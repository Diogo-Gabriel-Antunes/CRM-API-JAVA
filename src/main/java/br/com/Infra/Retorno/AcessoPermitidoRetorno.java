package br.com.Infra.Retorno;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessoPermitidoRetorno {

    private String token;
    private Boolean autenticado;

    public AcessoPermitidoRetorno(String token) {
        this.token = token;
        this.autenticado = true;
    }
}
