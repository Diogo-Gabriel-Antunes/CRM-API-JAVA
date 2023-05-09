package br.com.Resource;

import br.com.Service.FonteService;
import br.com.Service.FunilService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("funil")
public class FunilResource {

    @Inject
    private FunilService funilService;


    @GET
    public Response listAll(){
        return  funilService.findAll();
    }

    @GET
    @Path("select")
    public Response listForSelect(){
        return funilService.findBySelect();
    }

    @POST
    public Response create(String json){
        return funilService.create(json);
    }
}
