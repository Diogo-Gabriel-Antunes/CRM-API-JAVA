package br.com.Infra.Retorno;

import br.com.Infra.Exceptions.Mensagem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseNaoAutorizado {

    private List<Mensagem> mensagem = new ArrayList<>();
    private Integer statusCode;

    public ResponseNaoAutorizado(Mensagem mensagem, Integer statusCode) {
        this.mensagem.add(mensagem);
        this.statusCode = statusCode;
    }

    public ResponseNaoAutorizado(List<Mensagem> validacao) {
        this.mensagem = validacao;
        this.statusCode = 401;
    }
}
