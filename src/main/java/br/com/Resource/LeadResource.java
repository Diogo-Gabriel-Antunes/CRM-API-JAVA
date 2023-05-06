package br.com.Resource;

import br.com.Service.LeadService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("lead")
public class LeadResource {

    @Inject
    private LeadService leadService;

    @GET
    @Path("drag-drop")
    public Response getLeadsByFunil(@QueryParam("funilUuid")String funilUuid){
        return leadService.leadsByFunilUuid(funilUuid);
    }

    @POST
    @Transactional
    public Response createLead(String json){
        return leadService.create(json);
    }

    @POST
    @Transactional
    @Path("lote")
    public Response createLeadLote(String json){
        return leadService.createLote(json);
    }
}
