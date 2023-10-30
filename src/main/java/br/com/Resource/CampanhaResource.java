package br.com.Resource;

import br.com.Service.CampanhaService;
import br.com.Service.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("campanha")
public class CampanhaResource {

    @Inject
    private CampanhaService campanhaService;


    @GET
    public Response listAll(@QueryParam("offset")Integer offset,
    @QueryParam("soAtivo")boolean ativo){
        return  campanhaService.findAll(offset,ativo);
    }

    @GET
    @Path("{uuid}")
    public Response listForSelect(@PathParam("uuid") String uuid){
        return campanhaService.findOne(uuid);
    }
    @GET
    @Path("select")
    public Response listForSelect(){
        return campanhaService.findBySelect();
    }

    @POST
    public Response create(String json){
        return campanhaService.create(json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid") String uuid,String json){
        return campanhaService.update(uuid,json);
    }

    @DELETE
    @Path("{uuid}")
    public Response delete(@PathParam("uuid")String uuid){
        return campanhaService.delete(uuid);
    }

    @PUT
    @Path("alterar-status/{uuid}")
    public Response alterarStatus (@PathParam("uuid")String uuid){
        return campanhaService.updateStatus(uuid);
    }
    @PUT
    @Path("vincular-funil/{uuid}/{funilUuid}")
    public Response vincularFunil (@PathParam("uuid")String uuid,@PathParam("funilUuid") String funilUuid){
        return campanhaService.vincularFunil(uuid,funilUuid);
    }
}
