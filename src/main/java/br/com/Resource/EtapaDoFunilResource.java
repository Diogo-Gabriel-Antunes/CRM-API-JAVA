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
    @GET
    @Path("{uuid}")
    public Response getOne(@PathParam("uuid") String uuid){
        return etapaDoFunilService.findOne(uuid);
    }

    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid") String uuid,String json){
        return etapaDoFunilService.update(uuid,json);
    }
    @POST
    @Transactional
    public Response create(String json){
        return etapaDoFunilService.create(json);
    }


    @PUT
    @Path("alterar-ativo/{uuid}")
    @Transactional
    public Response alterarAtivo(@PathParam("uuid") String uuid){
        return etapaDoFunilService.alterarAtivo(uuid);
    }
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


    @DELETE
    @Path("{uuid}")
    @Transactional
    public Response delete(@PathParam("uuid") String uuid){
     return etapaDoFunilService.delete(uuid);
    }

}
