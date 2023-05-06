package br.com.Service;

import br.com.Invokers.FuncIVK.CampanhaTableIVK;
import br.com.Invokers.FuncIVK.SelectIVK;
import br.com.Invokers.IVK.CampanhaTableIVKDTO;
import br.com.Model.Campanha;
import br.com.Repository.CampanhaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CampanhaService extends Service {

    @Inject
    private CampanhaRepository campanhaRepository;

    public Response findAll() {
        List<Campanha> campanhas = campanhaRepository.findAll();
        List<CampanhaTableIVKDTO> ivkdtos = new ArrayList<>();
        for (Campanha campanha : campanhas) {
            CampanhaTableIVKDTO campanhaTableIVKDTO = new CampanhaTableIVKDTO();
            fieldUtil.invokerExecutor(new CampanhaTableIVK(campanha,campanhaTableIVKDTO));
            ivkdtos.add(campanhaTableIVKDTO);
        }
        return Response.ok(ivkdtos).build();

    }

    public Response findBySelect() {
        List<Campanha> campanhas = campanhaRepository.findAll();
        List<br.com.Invokers.IVK.SelectIVKDTO> campanhaSelectIVKDTOS = new ArrayList<>();

        for (Campanha campanha : campanhas) {
            br.com.Invokers.IVK.SelectIVKDTO campanhaSelectIVKDTO = new br.com.Invokers.IVK.SelectIVKDTO();
            fieldUtil.invokerExecutor(new SelectIVK(campanha, campanhaSelectIVKDTO));
            campanhaSelectIVKDTOS.add(campanhaSelectIVKDTO);
        }
        return Response.ok(campanhaSelectIVKDTOS).build();
    }

    public Response create(String json) {
        return  null;
    }
}
