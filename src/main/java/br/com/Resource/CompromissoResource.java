package br.com.Resource;


import br.com.Service.CompromissoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("compromisso")
public class CompromissoResource {

    @Inject
    private CompromissoService compromissoService;
    @POST
    @Path("oportunidade")
    @Transactional
    public Response createOportunidade(String json){
        return compromissoService.createCompromissoByOportunidade(json);
    }
    @POST
    @Path("tarefa")
    @Transactional
    public Response createTarefa(String json){
        return compromissoService.createCompromissoByTarefa(json);
    }
}
