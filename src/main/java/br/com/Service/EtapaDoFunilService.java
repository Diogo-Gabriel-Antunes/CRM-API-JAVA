package br.com.Service;

import br.com.DTO.EtapaDoFunilDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Invokers.FuncIVK.EtapaFunilTableIVK;
import br.com.Invokers.FuncIVK.FuncTableEtapaFunilIVK;
import br.com.Invokers.FuncIVK.SelectIVK;
import br.com.Invokers.IVK.EtapaFunilTableIVKDTO;
import br.com.Invokers.IVK.TableEtapaFunilIVK;
import br.com.Model.EtapaDoFunil;
import br.com.Repository.EtapaDoFunilRepository;

import br.com.Repository.FunilRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.BooleanUtils;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EtapaDoFunilService extends Service {

    @Inject
    private FunilRepository funilRepository;

    @Inject
    private EtapaDoFunilRepository etapaDoFunilRepository;

    public Response findAll(String funilUuid, Integer offset, String etapa, Integer nivel, Boolean finalizacao) {
        List<EtapaDoFunil> funils = etapaDoFunilRepository.findAll(funilUuid,offset,etapa,nivel,finalizacao);

        List<TableEtapaFunilIVK> tableEtapaFunils = new ArrayList<>();

        if(funils != null ){
            for (EtapaDoFunil funil : funils) {
                TableEtapaFunilIVK tableEtapaFunilIVK = new TableEtapaFunilIVK();
                fieldUtil.invokerExecutor(new FuncTableEtapaFunilIVK(tableEtapaFunilIVK, funil));
                tableEtapaFunils.add(tableEtapaFunilIVK);
            }
            return Response.ok(tableEtapaFunils).build();
        }else{
            return Response.ok(new ArrayList<>()).build();
        }
    }

    public Response findBySelect(String funilUuid) {
        List<EtapaDoFunil> etapaDoFunils = null;
        if (funilUuid != null) {
            etapaDoFunils = etapaDoFunilRepository.findAll(funilUuid);
        } else {
            etapaDoFunils = etapaDoFunilRepository.findByFunilPadrao();
        }
        List<br.com.Invokers.IVK.SelectIVKDTO> selectIVKDTOS = new ArrayList<>();

        for (EtapaDoFunil etapaDoFunil : etapaDoFunils) {
            br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO = new br.com.Invokers.IVK.SelectIVKDTO();
            fieldUtil.invokerExecutor(new SelectIVK.SelectIVKEtapaFunil(etapaDoFunil, selectIVKDTO));
            selectIVKDTOS.add(selectIVKDTO);
        }
        return Response.ok(selectIVKDTOS).build();
    }

    public Response listByFunil(String funilUuid) {
        List<EtapaDoFunil> etapaDoFunils = null;
        if (funilUuid != null) {
            etapaDoFunils = etapaDoFunilRepository.findByFunil(funilUuid);
        } else {
            etapaDoFunils = etapaDoFunilRepository.findByFunilPadrao();
        }

        List<EtapaFunilTableIVKDTO> ivkdtos = new ArrayList<>();
        for (EtapaDoFunil funil : etapaDoFunils) {
            EtapaFunilTableIVKDTO ivkdto = new EtapaFunilTableIVKDTO();
            fieldUtil.invokerExecutor(new EtapaFunilTableIVK(funil, ivkdto));
            ivkdtos.add(ivkdto);
        }
        return Response.ok(ivkdtos).build();
    }

    public Response create(String json) {
        EtapaDoFunilDTO dto = gson.fromJson(json, EtapaDoFunilDTO.class);
        validaDTO(dto);
        EtapaDoFunil etapaDoFunil = createEtapaFunil(dto);
        em.persist(etapaDoFunil);
        return Response.ok(etapaDoFunil).build();
    }

    public EtapaDoFunil createEtapaFunil(EtapaDoFunilDTO dto) {
        EtapaDoFunil etapaDoFunil = new EtapaDoFunil();
        etapaDoFunil.setEtapa(dto.getEtapa());
        etapaDoFunil.setAtivo(true);
        if (dto.getFunil() != null && StringUtil.stringValida(dto.getFunil().getUuid())) {
            etapaDoFunil.setFunil(funilRepository.findByUuid(dto.getFunil().getUuid()));
        }
        etapaDoFunil.setNivel(Integer.parseInt(dto.getNivel()));
        etapaDoFunil.setFinalizacao(dto.getFinalizacao());
        return etapaDoFunil;
    }


    public void validaDTO(EtapaDoFunilDTO dto) {
        validaDTO(new Validacoes(), dto);
    }

    public void validaDTO(Validacoes validacoes, EtapaDoFunilDTO dto) {
        Boolean validacoesNotExists = validacoes == null;
        if (validacoesNotExists) {
            validacoes = new Validacoes();
        }

        if (StringUtil.stringValida(dto.getEtapa())) {
            validacoes.add("Nome Etapa Invalido", "Insira um nome etapa valido");
        }

        if (dto.getFunil() == null || StringUtil.stringValida(dto.getFunil().getUuid())) {
            validacoes.add("Funil invalido", "Insira um funil valido");
        }

        if (validacoesNotExists) {
            validacoes.lancaErro();
        }
    }



    public Response etapasFunilByLead(String funilUuid) {
        List<EtapaDoFunil> etapas = null;
        if (StringUtil.stringValida(funilUuid)) {
            etapas = etapaDoFunilRepository.findByFunil(funilUuid);
        } else {
            etapas = etapaDoFunilRepository.findByFunilPadrao();
        }
        if (etapas == null || etapas.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(etapas).build();
        }
    }

    public Response findOne(String uuid) {

        EtapaDoFunil etapaDoFunil = etapaDoFunilRepository.findByUuid(uuid);
        if (etapaDoFunil == null) {
            throw new IllegalStateException("Etapa não encontrada");
        } else {
            return Response.ok(etapaDoFunil).build();
        }
    }

    public Response update(String uuid, String json) {
        EtapaDoFunilDTO etapaDoFunilDTO = gson.fromJson(json, EtapaDoFunilDTO.class);
        EtapaDoFunil etapaDoFunil = etapaDoFunilRepository.findByUuid(uuid);

        updateEtapaDoFunil(etapaDoFunilDTO, etapaDoFunil);
        em.persist(etapaDoFunil);
        return Response.ok(etapaDoFunil).build();
    }

    private void updateEtapaDoFunil(EtapaDoFunilDTO etapaDoFunilDTO, EtapaDoFunil etapaDoFunil) {
        etapaDoFunil.setEtapa(etapaDoFunilDTO.getEtapa());
        etapaDoFunil.setNivel(Integer.parseInt(etapaDoFunilDTO.getNivel()));
        etapaDoFunil.setAtivo(etapaDoFunilDTO.getAtivo());
        etapaDoFunil.setFinalizacao(etapaDoFunilDTO.getFinalizacao());
    }

    public Response delete(String uuid) {
        etapaDoFunilRepository.findByUuid(uuid).delete();
        return Response.ok().build();
    }

    public Response alterarAtivo(String uuid) {
        EtapaDoFunil etapaDoFunil = etapaDoFunilRepository.findByUuid(uuid);

        if(BooleanUtils.isTrue(etapaDoFunil.getAtivo())){
            etapaDoFunil.setAtivo(false);
        }else{
            etapaDoFunil.setAtivo(true);
        }
        em.persist(etapaDoFunil);
        return Response.ok().build();
    }

}
