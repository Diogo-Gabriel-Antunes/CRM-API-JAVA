package br.com.Service;

import br.com.DTO.ContatoDTO;
import br.com.Infra.Exceptions.Validacoes;
import org.acme.Util.PrimitiveUtil.StringUtil;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContatoService extends Service{

    public void validaContato(ContatoDTO contatoDTO){
        validaContato(contatoDTO,null);
    }

    public void validaContato(ContatoDTO contatoDTO, Validacoes validacoes){
        Boolean validacoesNotExists = validacoes == null;
        if(validacoesNotExists){
            validacoes = new Validacoes();
        }
        if (!StringUtil.stringValida(contatoDTO.getEmail())){
            validacoes.add("E-mail invalido","Verifique se inseriu um e-mail valido");
        }

        if(!StringUtil.stringValida(contatoDTO.getTelefone())){
            validacoes.add("Telefone invalido","Verifique se inseriu um telefone valido");
        }

        if(validacoesNotExists){
            validacoes.lancaErro();
        }
    }
}
