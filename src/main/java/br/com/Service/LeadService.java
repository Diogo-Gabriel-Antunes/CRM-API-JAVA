package br.com.Service;

import br.com.DTO.DragDropLeadDTO;
import br.com.DTO.LeadDTO;
import br.com.DTO.LeadLoteDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Model.*;
import br.com.Repository.EtapaDoFunilRepository;
import br.com.Repository.LeadRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class LeadService extends Service {

    @Inject
    private LeadRepository leadRepository;
    @Inject
    private EtapaDoFunilRepository etapaDoFunilRepository;
    public Response leadsByFunilUuid(String funilUuid) {
        List<Lead> leads = null;
        List<EtapaDoFunil> etapas = null;
        if (StringUtil.stringValida(funilUuid)) {
            leads = leadRepository.findByFunil(funilUuid);
            etapas = etapaDoFunilRepository.findByFunil(funilUuid);
        } else {
            leads = leadRepository.findByFunilPadrao();
            etapas = etapaDoFunilRepository.findByFunilPadrao();
        }
        List<DragDropLeadDTO> dragDropLeadDTO = montaDragDrop(leads,etapas);

        return Response.ok(dragDropLeadDTO).build();
    }

    private List<DragDropLeadDTO> montaDragDrop(List<Lead> leads, List<EtapaDoFunil> etapas) {
        List<DragDropLeadDTO> dragDropLeadDTOS = new ArrayList<>();
        if(etapas != null && !etapas.isEmpty()){
            for (EtapaDoFunil etapa : etapas) {
                DragDropLeadDTO dragDropLeadDTO = new DragDropLeadDTO();
                List<Lead> leadStream = leads.stream().filter(lead -> lead.getEtapaDoFunil().getUuid().equals(etapa.getUuid())).toList();
                List<DragDropLeadDTO.ItensDragDrop> itensDragDrops = new ArrayList<>();
                for (int i = 0; i < leadStream.size(); i++) {
                    DragDropLeadDTO.ItensDragDrop item = new DragDropLeadDTO.ItensDragDrop();
                    Lead lead = leadStream.get(i);
                    item.setLead(lead);
                    item.setPosicao(i);
                    item.setRelevancia(DragDropLeadDTO.ItensDragDrop.Relevancia.BAIXA);
                    itensDragDrops.add(item);
                }
                dragDropLeadDTO.setEtapaUuid(etapa.getUuid());
                dragDropLeadDTO.setEtapaName(etapa.getEtapa());
                dragDropLeadDTO.setLeads(itensDragDrops);
                dragDropLeadDTOS.add(dragDropLeadDTO);
            }
        }
        return dragDropLeadDTOS;
    }

    public Response create(String json) {
        LeadDTO leadDTO = gson.fromJson(json, LeadDTO.class);
        Lead lead = montaEValidaLead(leadDTO);
        em.persist(lead);
        return Response.ok(lead).build();
    }

    private Lead montaEValidaLead(LeadDTO leadDTO) {
        validaDTO(leadDTO);
        Lead lead = montaLead(leadDTO);
        return lead;
    }

    private Lead montaLead(LeadDTO leadDTO) {
        Lead lead = new Lead();
        lead.setDescricao(leadDTO.getDescricao());
        lead.setData(leadDTO.getData());
        if(leadDTO.getFunilUuid() != null){
            lead.setFunil(Funil.findById(leadDTO.getFunilUuid()));
        }
        if(leadDTO.getEtapaDoFunilUuid() != null){
            lead.setEtapaDoFunil(EtapaDoFunil.findById(leadDTO.getEtapaDoFunilUuid()));
        }
        if(leadDTO.getCampanhaUuid() != null){
            lead.setCampanha(Campanha.findById(leadDTO.getCampanhaUuid()));
        }
        lead.setValor(leadDTO.getValor());
        if(leadDTO.getClienteUuid() != null){
            lead.setCliente(Cliente.findById(leadDTO.getClienteUuid()));
        }
        return lead;
    }

    public void validaDTO(LeadDTO leadDTO) {
        validaDTO(new Validacoes(), leadDTO);
    }

    public void validaDTO(Validacoes validacoes, LeadDTO leadDTO) {
        Boolean validacaoNotExist = validacoes == null;
        if (validacaoNotExist) {
            validacoes = new Validacoes();
        }

        if (!StringUtil.stringValida(leadDTO.getClienteUuid())) {
            validacoes.add("Cliente invalido", "Informe um cliente valido");
        }

        if (!StringUtil.stringValida(leadDTO.getFunilUuid())) {
            validacoes.add("Funil invalido", "Favor informar um funil valido");
        }

        if (!StringUtil.stringValida(leadDTO.getDescricao())) {
            validacoes.add("Descrição invalida", "Insira uma descrição valida");
        }

        if (!StringUtil.stringValida(leadDTO.getEtapaDoFunilUuid())) {
            validacoes.add("Etapa do funil invalia", "Favor inserir etapa do funil valida");
        }

        if (leadDTO.getValor() == null) {
            validacoes.add("Valor invalido", "Favor inserir um valor valido");
        }

        if (validacaoNotExist) {
            validacoes.lancaErro();
        }
    }


    public Response createLote(String json) {
        LeadLoteDTO lote =  gson.fromJson(json, LeadLoteDTO.class);
        List<Lead> leads = new ArrayList<>();
        if(lote != null && lote.getLote() != null && !lote.getLote().isEmpty()){
            for (LeadDTO leadDTO : lote.getLote()) {
                Lead lead = montaEValidaLead(leadDTO);
                em.persist(lead);
                leads.add(lead);
            }
            return Response.ok(leads).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    public Response updateDragDrop(String uuid, String etapaName) {
        if(StringUtil.stringValida(etapaName)){
            Lead lead = leadRepository.findByUuid(uuid);
            EtapaDoFunil etapa = etapaDoFunilRepository.findByFunilAndName(lead.getFunil(),etapaName);
            lead.setEtapaDoFunil(etapa);
            em.merge(lead);
            return Response.ok(lead).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    public Response getAnotacoes(String uuid) {
        Lead lead = leadRepository.getAnotacoesByUuid(uuid);
        if(lead != null){
            return Response.ok(lead).build();
        }else{
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
