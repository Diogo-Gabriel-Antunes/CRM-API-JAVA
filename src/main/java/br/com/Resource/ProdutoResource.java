package br.com.Resource;


import br.com.Service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("produto")
public class ProdutoResource {


    @Inject
    private ProdutoService produtoService;


    @GET
    public Response listAll(@QueryParam("offset") Integer offset) {
        return produtoService.listAll(offset);
    }

    @GET
    @Path("{uuid}")
    public Response getOne(@PathParam("uuid") String uuid) {
        return produtoService.getOne(uuid);
    }

    @GET
    @Path("materia-prima/{uuid}")
    public Response getMateriaPrima(@PathParam("uuid") String uuid) {
        return produtoService.getMateriaPrima(uuid);
    }


    @POST
    public Response create(String json) {
        return produtoService.create(json);
    }
    @POST
    @Path("itens/{uuid}")
    @Transactional
    public Response create(@PathParam("uuid")String uuid, String json) {
        return produtoService.createItens(uuid,json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid") String uuid, String json) {
        return produtoService.update(uuid, json);
    }

    @DELETE
    @Path("{uuid}")
    public Response delete(@PathParam("uuid")String uuid){
        return produtoService.delete(uuid);
    }
}

