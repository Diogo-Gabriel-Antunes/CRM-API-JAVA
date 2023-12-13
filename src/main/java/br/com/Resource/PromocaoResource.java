package br.com.Resource;

import br.com.Service.PromocaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("promocao")
public class PromocaoResource {


    @Inject
    private PromocaoService promocaoService;

    @GET
    public Response getAll(@QueryParam("offset") Integer offset,
                           @QueryParam("limit") @DefaultValue("10") Integer limit) {
        return promocaoService.getAll(offset,limit);
    }

    @GET
    @Path("{uuid}")
    public Response getOne(@PathParam("uuid")String uuid){
        return promocaoService.getOne(uuid);
    }

    @POST
    public Response create(String json){
        return promocaoService.create(json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String json){
        return promocaoService.update(uuid,json);
    }
}
