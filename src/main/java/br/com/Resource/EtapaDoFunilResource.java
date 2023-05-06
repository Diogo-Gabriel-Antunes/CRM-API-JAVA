package br.com.Resource;

import br.com.Service.EtapaDoFunilService;
import br.com.Service.FunilService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("etapa-funil")
public class EtapaDoFunilResource {

    @Inject
    private EtapaDoFunilService etapaDoFunilService;


    @GET
    public Response listAll(@QueryParam("funilUuid") String funilUuid){
        return  etapaDoFunilService.findAll(funilUuid);
    }

    @POST
    @Transactional
    public Response create(String json){
        return etapaDoFunilService.create(json);
    }
    @PUT
    @Path("desativar/{uuid}")
    @Transactional
    public Response updateDesativar(@PathParam("uuid")String uuid){
        return etapaDoFunilService.desativar(uuid);
    }
    @PUT
    @Path("ativar/{uuid}")
    @Transactional
    public Response updateAtivar(@PathParam("uuid")String uuid){
        return etapaDoFunilService.ativar(uuid);
    }

    @GET
    @Path("select")
    public Response listForSelect(@QueryParam("funilUuid") String funilUuid){
        return etapaDoFunilService.findBySelect(funilUuid);
    }

    @GET
    @Path("funil/{funilUuid}")
    public Response listByFunil(@PathParam("funilUuid") String funilUuid){
        return etapaDoFunilService.listByFunil(funilUuid);
    }

    @GET
    @Path("lead")
    public Response etapasDoFunilByLead(@QueryParam("funilUuid")String funilUuid){
        return etapaDoFunilService.etapasFunilByLead(funilUuid);
    }
}
