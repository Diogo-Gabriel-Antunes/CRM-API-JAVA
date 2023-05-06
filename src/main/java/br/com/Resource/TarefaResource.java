package br.com.Resource;

import br.com.Service.TarefaService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("tarefa")
public class TarefaResource {

    @Inject
    private TarefaService tarefaService;
    @POST
    @Transactional
    public Response create(String json){
        return tarefaService.create(json);
    }

    @GET
    public Response listAll(){
        return tarefaService.listAll();
    }
}
