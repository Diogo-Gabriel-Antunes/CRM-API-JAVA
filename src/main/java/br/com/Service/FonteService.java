package br.com.Service;

import br.com.Invokers.FuncIVK.SelectIVK;
import br.com.Model.Fonte;
import br.com.Repository.FonteRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FonteService extends Service {

    @Inject
    private FonteRepository fonteRepository;

    public Response findAll() {
        List<Fonte> fontes = fonteRepository.findAll();
        return Response.ok(fontes).build();

    }

    public Response findBySelect() {
        List<Fonte> fontes = fonteRepository.findAll();
        List<br.com.Invokers.IVK.SelectIVKDTO> selectIVKDTOS = new ArrayList<>();

        for (Fonte fonte : fontes) {
            br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO = new br.com.Invokers.IVK.SelectIVKDTO();
            fieldUtil.invokerExecutor(new SelectIVK.SelectIVKFonte(fonte,selectIVKDTO));
            selectIVKDTOS.add(selectIVKDTO);
        }
        return Response.ok(selectIVKDTOS).build();
    }
}
