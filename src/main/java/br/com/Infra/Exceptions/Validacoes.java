package br.com.Infra.Exceptions;

import com.google.gson.Gson;
import lombok.Data;
import org.acme.Util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

@Data
public class Validacoes extends RuntimeException{

    private List<Mensagem> validacao = new ArrayList<>();

    private Gson gson = new GsonUtil().parser;

    public Validacoes() {
    }

    public void add(String erro, String solucao){
        Mensagem mensagem = new Mensagem();
        mensagem.setErro(erro);
        mensagem.setSolucao(solucao);
        validacao.add(mensagem);
    }

    public void lancaErro(){
        if(!validacao.isEmpty()){
            throw new Validacoes();
        }
    }
}
