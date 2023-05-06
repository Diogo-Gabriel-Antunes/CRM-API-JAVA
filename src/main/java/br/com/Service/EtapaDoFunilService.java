package br.com.Service;

import br.com.DTO.EtapaDoFunilDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Invokers.FuncIVK.EtapaFunilTableIVK;
import br.com.Invokers.FuncIVK.SelectIVK;
import br.com.Invokers.IVK.EtapaFunilTableIVKDTO;
import br.com.Model.EtapaDoFunil;
import br.com.Repository.EtapaDoFunilRepository;

import br.com.Repository.FunilRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EtapaDoFunilService extends Service {

    @Inject
    private FunilRepository funilRepository;

    @Inject
    private EtapaDoFunilRepository etapaDoFunilRepository;

    public Response findAll(String funilUuid) {
        List<EtapaDoFunil> funils = etapaDoFunilRepository.findAll(funilUuid);

        return Response.ok(funils).build();

    }

    public Response findBySelect(String funilUuid) {
        List<EtapaDoFunil> etapaDoFunils = null;
        if(funilUuid != null){
            etapaDoFunils = etapaDoFunilRepository.findAll(funilUuid);
        }else{
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
        if(funilUuid != null){
            etapaDoFunils = etapaDoFunilRepository.findByFunil(funilUuid);
        }else{
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
        etapaDoFunil.setAtivo(dto.getAtivo());
        if (dto.getFunil() != null && StringUtil.stringValida(dto.getFunil().getUuid())) {
            etapaDoFunil.setFunil(funilRepository.findByUuid(dto.getFunil().getUuid()));
        }

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

    public Response desativar(String uuid) {
        EtapaDoFunil etapaDoFunil = etapaDoFunilRepository.findByUuid(uuid);
        etapaDoFunil.setAtivo(false);
        em.persist(etapaDoFunil);
        return Response.ok(etapaDoFunil).build();

    }

    public Response ativar(String uuid) {
        EtapaDoFunil etapaDoFunil = etapaDoFunilRepository.findByUuid(uuid);
        etapaDoFunil.setAtivo(true);
        em.persist(etapaDoFunil);
        return Response.ok(etapaDoFunil).build();
    }

    public Response etapasFunilByLead(String funilUuid) {
        List<EtapaDoFunil> etapas = null;
        if (StringUtil.stringValida(funilUuid)) {
            etapas = etapaDoFunilRepository.findByFunil(funilUuid);
        } else {
            etapas = etapaDoFunilRepository.findByFunilPadrao();
        }
        if (etapas ==  null || etapas.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok(etapas).build();
        }
    }
}
