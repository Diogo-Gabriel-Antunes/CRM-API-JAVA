package br.com.Security.Service;

import br.com.Security.Infra.TokenException;
import br.com.Security.Model.Usuario;
import br.com.Service.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@ApplicationScoped
public class JWTService extends Service {

    Algorithm algorithm = Algorithm.HMAC256("Teste");

    public String gerarToken(Usuario usuario){
        try{
            return JWT.create()
                    .withIssuer("CRM-Dickael")
                    .withSubject(usuario.getEmail())
                    .withClaim("uuid",usuario.getUuid())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token",exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {
        try{
            return JWT.require(algorithm)
                    .withIssuer("CRM-Dickael")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            throw new TokenException("Token Invalido ou seu tempo de expiração acabou");
        }
    }

    public String validaHeaderAndReturnToken(String authorization) {
        if(authorization.isEmpty()){
           throw new TokenException("Token vazio");
        }

        if(!authorization.contains("Bearer ")){
            throw new TokenException("Token mal formatado");
        }

        return authorization.replace("Bearer ", "");

    }
}
