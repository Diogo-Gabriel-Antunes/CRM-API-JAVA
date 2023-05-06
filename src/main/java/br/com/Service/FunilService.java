package br.com.Service;

import br.com.Invokers.FuncIVK.FunilTableIVK;
import br.com.Invokers.FuncIVK.SelectIVK;
import br.com.Invokers.IVK.FunilTableIVKDTO;
import br.com.Model.Funil;
import br.com.Repository.FunilRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FunilService extends Service {

    @Inject
    private FunilRepository funilRepository;

    public Response findAll() {
        List<Funil> funils = funilRepository.findAll();
        List<FunilTableIVKDTO> ivk = new ArrayList<>();
        for (Funil funil : funils) {
            FunilTableIVKDTO funilTableIVKDTO = new FunilTableIVKDTO();
            fieldUtil.invokerExecutor(new FunilTableIVK(funil,funilTableIVKDTO));
            ivk.add(funilTableIVKDTO);
        }
        return Response.ok(ivk).build();

    }

    public Response findBySelect() {
        List<Funil> funils = funilRepository.findAll();
        List<br.com.Invokers.IVK.SelectIVKDTO> selectIVKDTOS = new ArrayList<>();

        for (Funil funil : funils) {
            br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO = new br.com.Invokers.IVK.SelectIVKDTO();
            fieldUtil.invokerExecutor(new SelectIVK.SelectIVKFunil(funil,selectIVKDTO));
            selectIVKDTOS.add(selectIVKDTO);
        }
        return Response.ok(selectIVKDTOS).build();
    }
}
