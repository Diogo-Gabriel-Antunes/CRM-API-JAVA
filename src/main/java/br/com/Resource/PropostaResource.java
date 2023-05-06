package br.com.Resource;

import br.com.Service.PropostaService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("proposta")
public class PropostaResource {

    @Inject
    private PropostaService propostaService;

    @POST
    @Transactional
    public Response create(String json){
        return propostaService.create(json);
    }
}
