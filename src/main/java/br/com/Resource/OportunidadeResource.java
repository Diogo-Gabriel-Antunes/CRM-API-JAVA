package br.com.Resource;

import br.com.Model.Enum.Mes;
import br.com.Model.Oportunidade;
import br.com.Repository.OportunidadeRepository;
import br.com.Service.OportunidadeService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("oportunidade")
public class OportunidadeResource {

    @Inject
    OportunidadeRepository oportunidadeRepository;

    @Inject
    OportunidadeService oportunidadeService;


    @GET
    public Response listAll() {
        return Response.ok(oportunidadeRepository.findAllByUser()).build();
    }

    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid") String uuid) {
        Oportunidade oportunidade = oportunidadeRepository.findByUuid(uuid);
        if (oportunidade != null) {
            return Response.ok(oportunidade).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }



    @POST
    @Transactional
    public Response create(String json) {
        return oportunidadeService.create(json);
    }

    @POST
    @Path("compromisso")
    @Transactional
    public Response createCompromisso(String json) {
        return oportunidadeService.createComCompromisso(json);
    }

    @POST
    @Path("acoes")
    @Transactional
    public Response createByAcoes(String json) {
        return oportunidadeService.createByAcoes(json);
    }
}
