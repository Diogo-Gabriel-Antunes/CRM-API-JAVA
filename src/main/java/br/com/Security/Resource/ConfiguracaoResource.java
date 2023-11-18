package br.com.Security.Resource;

import br.com.Security.DTO.UsuarioLogado;
import br.com.Security.Model.Configuracao;
import br.com.Security.Model.Usuario;
import br.com.Security.Service.ConfiguracaoService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("configuracao")
public class ConfiguracaoResource {

    @Inject
    ConfiguracaoService configuracaoService;

    @POST
    @Transactional
    public Response create(String json){
        return  configuracaoService.create(json);
    }

    @GET
    public Response getConfig(){
        Configuracao configuracao = UsuarioLogado.getUsuario().getConfiguracao();
        return Response.ok(configuracao).build();
    }

    @GET
    @Path("jornada-de-trabalho")
    public Response getJornadaDeTrabalho(){
        return configuracaoService.getJornadaDeTrabalho();
    }
    @GET
    @Path("jornada-de-trabalho/select")
    public Response getJornadaDeTrabalhoSelect(){
        return configuracaoService.getJornadaDeTrabalhoSelect();
    }
}
