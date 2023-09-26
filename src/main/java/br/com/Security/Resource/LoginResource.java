package br.com.Security.Resource;

import br.com.Security.Builder.FactoryResponseLogin;
import br.com.Security.Service.JWTService;
import br.com.Security.Service.LoginService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.StringUtil;

@Path("login")
public class LoginResource {

    @Inject
    private LoginService loginService;

    @Inject
    private JWTService jwtService;
    @POST
    public Response login(String json){
        return loginService.login(json);
    }
    @GET
    public Response verificaAutenticacao(@HeaderParam("Authorization") String authorization){
        if(StringUtil.stringValida(authorization)){
            String token = jwtService.validaHeaderAndReturnToken(authorization);
            if(StringUtil.stringValida(token)){
                return FactoryResponseLogin.acessoPermitido(token);
            }else{
                return FactoryResponseLogin.semPermissaoGeral();
            }
        }else{
            return FactoryResponseLogin.semPermissaoGeral();
        }
    }
}
