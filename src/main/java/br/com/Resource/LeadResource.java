package br.com.Resource;

import br.com.Service.LeadService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
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

    @PUT
    @Transactional
    @Path("drag-drop/{uuid}")
    public Response updateLead(@PathParam("uuid")String uuid,@QueryParam("etapaName")String etapaName){
        return leadService.updateDragDrop(uuid,etapaName);
    }

    @GET
    @Path("anotacoes/{uuid}")
    public Response getAnotacoes(@PathParam("uuid")String uuid ){
        return leadService.getAnotacoes(uuid);
    }
}
