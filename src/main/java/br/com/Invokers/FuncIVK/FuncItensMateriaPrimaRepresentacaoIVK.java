package br.com.Invokers.FuncIVK;

import br.com.Invokers.IVK.ItensMateriaPrimaRepresentacaoIVKDTO;
import br.com.Invokers.IVK.ProdutoRepresentacaoIVK;
import br.com.Model.ItensMateriaPrima;
import br.com.Model.Produto;
import org.acme.Util.InterfacesUtil.Invoker;

public class FuncItensMateriaPrimaRepresentacaoIVK implements Invoker {

    private ItensMateriaPrima model;
    private ItensMateriaPrimaRepresentacaoIVKDTO dto;

    public FuncItensMateriaPrimaRepresentacaoIVK(ItensMateriaPrima model, ItensMateriaPrimaRepresentacaoIVKDTO dto) {
        this.model = model;
        this.dto = dto;
    }

    public void ivk (){
        dto.setNome(model.getMateriaPrimas().getNome());
        dto.setQuantidadeUtiliza(model.getQuantidadeUtiliza());

    }
}
