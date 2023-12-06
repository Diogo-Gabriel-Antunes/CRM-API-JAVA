package br.com.Invokers.FuncIVK;

import br.com.Invokers.IVK.ProdutoRepresentacaoIVK;
import br.com.Model.Produto;
import org.acme.Util.InterfacesUtil.Invoker;

public class FuncProdutoRepresentacaoIVK implements Invoker {

    private Produto model;
    private ProdutoRepresentacaoIVK dto;

    public FuncProdutoRepresentacaoIVK(Produto model, ProdutoRepresentacaoIVK dto) {
        this.model = model;
        this.dto = dto;
    }

    public void ivk (){
        dto.setNome(model.getNome());
        dto.setPreco(model.getPreco());

    }
}
