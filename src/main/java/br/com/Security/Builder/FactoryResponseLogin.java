package br.com.Security.Builder;

import br.com.Infra.Exceptions.Mensagem;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Infra.Retorno.AcessoPermitidoRetorno;
import br.com.Infra.Retorno.ResponseNaoAutorizado;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class FactoryResponseLogin {
    public static Response semPermissaoGeral() {
        Mensagem mensagem = new Mensagem();
        mensagem.setErro("Erro na requisição de login");
        mensagem.setSolucao("Favor verificar credenciais, caso persistir contatar o suporte");

        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ResponseNaoAutorizado(mensagem,401))
                .build();
    }

    public static Response semPermissaoCredenciaisInvalidas(Validacoes v) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(new ResponseNaoAutorizado(v.getValidacao())).build();
    }


    public static Response acessoPermitido(String token) {
        return Response.accepted(new AcessoPermitidoRetorno(token)).build();
    }

    public static Response senhaInvalida() {
        Mensagem mensagem = new Mensagem();
        mensagem.setErro("Senha invalida");
        mensagem.setSolucao("Favor verificar credenciais, caso persistir contatar o suporte");

        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ResponseNaoAutorizado(mensagem,401))
                .build();
    }
}
