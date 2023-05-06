package br.com.Security.Infra.Filter;

import br.com.Security.DTO.UsuarioLogado;
import br.com.Security.Infra.TokenException;
import br.com.Security.Model.Usuario;
import br.com.Security.Repository.UsuarioRepository;
import br.com.Security.Service.JWTService;
import io.vertx.core.http.HttpServerRequest;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;


@Provider
public class LoginFilter implements ContainerRequestFilter {
    @Context
    UriInfo info;

    @Context
    HttpServerRequest request;

    @Inject
    JWTService jwtService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        String token = null;
        if(!pathsPublicos(info.getPath())){
            Map<String, Cookie> cookies = ctx.getCookies();
            Cookie cookie = cookies.get("token");
            if(cookie != null && cookie.getValue() != null){
                token = cookie.getValue();
            }else if(request.getHeader("Authorization") != null){
                String authorization = request.getHeader("Authorization");
                token = jwtService.validaHeaderAndReturnToken(authorization);
            }else{
                throw new TokenException("Token invalido");
            }
            String subject = jwtService.getSubject(token);
            Usuario usuarioByToken = usuarioRepository.getByEmail(subject);
            UsuarioLogado.setUsuario(usuarioByToken);
        }


    }

    private boolean pathsPublicos(String path) {
        return path.contains("/login");
    }
}
