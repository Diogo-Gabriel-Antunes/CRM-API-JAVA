package br.com.Resource;

import br.com.Service.AnotacoesLeadService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("anotacoes-lead")
public class AnotacoesLeadResource {

    @Inject
    private AnotacoesLeadService anotacoesLeadService;

    @POST
    @Path("anotacao/{leadUuid}")
    @Transactional
    public Response createAnotacao(@PathParam("leadUuid")String leadUuid, String json){
        return anotacoesLeadService.createByAnotacao(leadUuid,json);
    }

}
