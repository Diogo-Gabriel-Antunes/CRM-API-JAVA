package br.com.Security.Service;

import br.com.Security.Builder.FactoryResponseLogin;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Security.DTO.UsuarioDTO;
import br.com.Security.Model.Usuario;
import br.com.Security.Repository.UsuarioRepository;
import br.com.Service.Service;
import io.quarkus.elytron.security.common.BcryptUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class LoginService extends Service {

    @Inject
    private UsuarioRepository repository;
    @Inject
    private JWTService jwtService;

    public Response login(String json) {
        try{
            UsuarioDTO usuarioDTO = gson.fromJson(json, UsuarioDTO.class);
            validaDTO(usuarioDTO);

            Usuario usuario = repository.getByEmail(usuarioDTO.getEmail());

            if(usuario == null){
                return FactoryResponseLogin.semPermissaoGeral();
            }

            if(!BcryptUtil.matches(usuarioDTO.getSenha(),usuario.getSenha())){
                return FactoryResponseLogin.senhaInvalida();
            }

            String token = jwtService.gerarToken(usuario);
            return FactoryResponseLogin.acessoPermitido(token);
        }catch (Validacoes v){
            return FactoryResponseLogin.semPermissaoCredenciaisInvalidas(v);
        }catch (Throwable t){
            t.printStackTrace();
            return FactoryResponseLogin.semPermissaoGeral();
        }
    }

    private void validaDTO(UsuarioDTO usuarioDTO) {
        Validacoes validacoes = new Validacoes();

        if(!StringUtil.stringValida(usuarioDTO.getEmail())){
           validacoes.add("Email invalido","Verifique se digitou um email valido");
        }
        if(!StringUtil.stringValida(usuarioDTO.getSenha())){
           validacoes.add("Senha invalida","Verifique se digitou uma senha valida");
        }

        validacoes.lancaErro();
    }
}
