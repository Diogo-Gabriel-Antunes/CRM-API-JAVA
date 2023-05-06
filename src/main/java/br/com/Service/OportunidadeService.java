package br.com.Service;

import br.com.DTO.Calendario.CalendarioDTO;
import br.com.DTO.OportunidadeDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Model.*;
import br.com.Model.Enum.Mes;
import org.acme.Util.PrimitiveUtil.StringUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Optional;

@ApplicationScoped
public class OportunidadeService extends Service {

    @Inject
    private CalendarioService calendarioService;

    @Inject
    private CompromissoService compromissoService;

    @Transactional
    public Response create(String json) {
        OportunidadeDTO dto = gson.fromJson(json, OportunidadeDTO.class);
        validaDTO(dto);
        Oportunidade oportunidade = createOportunidade(dto);
        oportunidade.persist();
        return Response.ok(oportunidade).build();
    }

    public Oportunidade createOportunidade(OportunidadeDTO dto) {
        Oportunidade oportunidade = new Oportunidade();
        if (dto.getFunil() != null) {
            Optional<Funil> funil = Funil.findByIdOptional(dto.getFunil().getUuid());
            funil.ifPresent(oportunidade::setFunil);
        }
        if (dto.getEtapaDoFunil() != null) {
            Optional<EtapaDoFunil> etapaDoFunil = EtapaDoFunil.findByIdOptional(dto.getEtapaDoFunil().getUuid());
            etapaDoFunil.ifPresent(oportunidade::setEtapaDoFunil);
        }
        if (dto.getFonte() != null) {
            Optional<Fonte> fonte = Fonte.findByIdOptional(dto.getFonte().getUuid());
            fonte.ifPresent(oportunidade::setFonte);
        }
        if (dto.getCampanha() != null) {
            Optional<Campanha> campanha = Campanha.findByIdOptional(dto.getCampanha().getUuid());
            campanha.ifPresent(oportunidade::setCampanha);
        }
        if (dto.getCliente() != null) {
            Optional<Cliente> cliente = Cliente.findByIdOptional(dto.getCliente().getUuid());
            cliente.ifPresent(oportunidade::setCliente);
        }
        if (dto.getEmpresa() != null) {
            Optional<Empresa> empresa = Empresa.findByIdOptional(dto.getEmpresa().getUuid());
            empresa.ifPresent(oportunidade::setEmpresa);
        }
        oportunidade.setNomeOportunidade(dto.getNomeOportunidade());
        return oportunidade;
    }

    public void validaDTO(OportunidadeDTO dto){
        validaDTO(null,dto);
    }
    public void validaDTO(Validacoes validacoes,OportunidadeDTO dto) {
        Boolean validacoesNotExists = validacoes == null;
        if(validacoesNotExists){
            validacoes = new Validacoes();
        }

        if (!StringUtil.stringValida(dto.getNomeOportunidade())) {
            validacoes.add("Nome da oportunidade invalido", "Necessario informar o nome da oportunidade");
        }
        if (dto.getCampanha() == null || !StringUtil.stringValida(dto.getCampanha().getUuid())) {
            validacoes.add("Campanha invalida", "Necessario informar o nome da campanha");
        }
        if (dto.getFonte() == null || !StringUtil.stringValida(dto.getFonte().getUuid())) {
            validacoes.add("Fonte invalida", "Necessario informar o nome da Fonte");
        }
        Boolean notExistsCliente = true;

        if (dto.getCliente() != null && dto.getCliente().getUuid() != null) {
            notExistsCliente = false;
        }

        if (dto.getEmpresa() != null && dto.getEmpresa().getUuid() != null) {
            notExistsCliente = false;
        }
        if (notExistsCliente) {
            validacoes.add("Cliente invalido", "É necessario informar um cliente");
        }

        if (validacoesNotExists){
            validacoes.lancaErro();
        }
    }

    public Response createByAcoes(String json) {
        OportunidadeDTO dto = gson.fromJson(json, OportunidadeDTO.class);
        validaDTO(dto);
        Oportunidade oportunidade = createOportunidade(dto);
        em.persist(oportunidade);

        return Response.ok(oportunidade).build();
    }



    public Response createComCompromisso(String json) {
        OportunidadeDTO dto = gson.fromJson(json, OportunidadeDTO.class);
        validaDTOCompromisso(dto);
        Oportunidade oportunidade = createOportunidade(dto);
        em.persist(oportunidade);
        Compromisso compromisso = compromissoService.createCompromisso(dto.getCompromisso(),oportunidade,null);
        em.persist(compromisso);
        return Response.ok(compromisso).build();
    }

    public void validaDTOCompromisso(OportunidadeDTO dto) {
        Validacoes validacoes = new Validacoes();

        validaDTO(validacoes,dto);
        compromissoService.validaDTO(validacoes,dto.getCompromisso());

        validacoes.lancaErro();
    }
}

