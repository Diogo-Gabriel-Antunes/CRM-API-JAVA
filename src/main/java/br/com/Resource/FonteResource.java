package br.com.Resource;

import br.com.Service.CampanhaService;
import br.com.Service.FonteService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("fonte")
public class FonteResource {

    @Inject
    private FonteService fonteService;


    @GET
    public Response listAll(){
        return  fonteService.findAll();
    }

    @GET
    @Path("select")
    public Response listForSelect(){
        return fonteService.findBySelect();
    }
}
