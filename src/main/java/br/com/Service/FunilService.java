package br.com.Service;

import br.com.DTO.FunilDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Invokers.FuncIVK.FunilTableIVK;
import br.com.Invokers.FuncIVK.SelectIVK;
import br.com.Invokers.IVK.FunilTableIVKDTO;
import br.com.Model.Funil;
import br.com.Repository.FunilRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.BooleanUtils;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FunilService extends Service {

    @Inject
    private FunilRepository funilRepository;

    public Response findAll(Integer offset, Boolean soAtivo) {
        List<Funil> funils = null;
        if(BooleanUtils.isTrue(soAtivo)){
            funils = funilRepository.findAllSoAtivo(offset);
        }else{
            funils = funilRepository.findAll(offset);
        }
        List<FunilTableIVKDTO> ivk = new ArrayList<>();
            for (Funil funil : funils) {
            FunilTableIVKDTO funilTableIVKDTO = new FunilTableIVKDTO();
            fieldUtil.invokerExecutor(new FunilTableIVK(funil, funilTableIVKDTO));
            ivk.add(funilTableIVKDTO);
        }
        return Response.ok(ivk).build();

    }

    public Response findBySelect() {
        List<Funil> funils = funilRepository.findAll();
        List<br.com.Invokers.IVK.SelectIVKDTO> selectIVKDTOS = new ArrayList<>();

        for (Funil funil : funils) {
            br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO = new br.com.Invokers.IVK.SelectIVKDTO();
            fieldUtil.invokerExecutor(new SelectIVK.SelectIVKFunil(funil, selectIVKDTO));
            selectIVKDTOS.add(selectIVKDTO);
        }
        return Response.ok(selectIVKDTOS).build();
    }

    @Transactional
    public Response create(String json) {
        FunilDTO funilDTO = gson.fromJson(json, FunilDTO.class);
        validaDTO(funilDTO);
        Funil funil = new Funil();
        funil.setNomeFunil(funilDTO.getNomeFunil());
        Funil funilPadrao = funilRepository.findPadrao();
        if (funilPadrao == null) {
            funil.setPadrao(true);
        } else {
            funil.setPadrao(false);
        }
        funil.setAtivo(true);
        em.persist(funil);
        return Response.ok(funil).build();
    }

    private void validaDTO(FunilDTO funilDTO) {
        Validacoes validacoes = new Validacoes();
        validaDTO(validacoes, funilDTO);
        validacoes.lancaErro();
    }

    public void validaDTO(Validacoes validacoes, FunilDTO funilDTO) {

        if (!StringUtil.stringValida(funilDTO.getNomeFunil())) {
            validacoes.add("Nome do funil Invalido", "Verifique se adicionou um nome valido");
        }

    }
}
