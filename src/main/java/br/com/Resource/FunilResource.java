package br.com.Resource;

import br.com.Service.FonteService;
import br.com.Service.FunilService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("funil")
public class FunilResource {

    @Inject
    private FunilService funilService;


    @GET
    public Response listAll(@QueryParam("offset")Integer offset,
                            @QueryParam("soAtivo")Boolean soAtivo,
                            @QueryParam("nomeFunil") String nomeFunil){
        return  funilService.findAll(offset,soAtivo,nomeFunil);
    }

    @GET
    @Path("{uuid}")
    public Response getOne(@PathParam("uuid") String uuid){
        return funilService.getOne(uuid);
    }

    @GET
    @Path("select")
    public Response listForSelect(){
        return funilService.findBySelect();
    }
    @GET
    @Path("padrao")
    public Response findPadrao(){
        return funilService.findPadrao();
    }


    @POST
    public Response create(String json){
        return funilService.create(json);
    }

    @DELETE
    @Path("{uuid}")
    public Response delete(@PathParam("uuid") String uuid){
        return funilService.delete(uuid);
    }
}
