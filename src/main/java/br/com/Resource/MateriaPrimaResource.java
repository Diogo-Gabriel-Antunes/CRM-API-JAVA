package br.com.Resource;

import br.com.Service.MateriaPrimaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("materia-prima")
public class MateriaPrimaResource {

    @Inject
    private MateriaPrimaService materiaPrimaService;

    @GET
    public Response getAll(@QueryParam("offset") Integer offset,
                           @QueryParam("limit") Integer limit,
                           @QueryParam("nome") String nome) {
        return materiaPrimaService.getAll(offset, limit, nome);
    }

    @GET
    @Path("{uuid}")
    public Response getOne(@PathParam("uuid")String uuid){
        return materiaPrimaService.getOne(uuid);
    }

    @POST
    public Response create(String json){
        return materiaPrimaService.create(json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid, String json){
        return materiaPrimaService.update(uuid,json);
    }
}
