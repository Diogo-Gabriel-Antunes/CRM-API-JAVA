package br.com.Security.Infra;

import br.com.Infra.Exceptions.Mensagem;
import br.com.Infra.Exceptions.Validacoes;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TokenException extends RuntimeException{

    private List<Mensagem> mensagems = new ArrayList<>();

    private Gson gson = new GsonUtil().parser;

    public TokenException(String message) {
        super(message);
    }

    public void add(String erro, String solucao){
        Mensagem mensagem = new Mensagem();
        mensagem.setErro(erro);
        mensagem.setSolucao(solucao);
        mensagems.add(mensagem);
    }

    public void lancaErro(){
        if(!mensagems.isEmpty()){
            throw new TokenException("");
        }
    }
}
