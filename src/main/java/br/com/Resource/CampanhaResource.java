package br.com.Resource;

import br.com.Service.CampanhaService;
import br.com.Service.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("campanha")
public class CampanhaResource {

    @Inject
    private CampanhaService campanhaService;


    @GET
    public Response listAll(){
        return  campanhaService.findAll();
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
}
