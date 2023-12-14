package br.com.Invokers.FuncIVK;

import br.com.Invokers.IVK.PromocaoRepresentacaoIVK;
import br.com.Model.Promocao;
import lombok.Data;
import org.acme.Util.InterfacesUtil.Invoker;

@Data
public class FuncPromocaoRepresentacaoIVK implements Invoker {
    private Promocao model;
    private PromocaoRepresentacaoIVK dto;

    public FuncPromocaoRepresentacaoIVK(Promocao model, PromocaoRepresentacaoIVK dto) {
        this.model = model;
        this.dto = dto;
    }

    public void invoker() {
        dto.setUuid(model.getUuid());
        dto.setNomePromocao(model.getNomePromocao());
        dto.setPorcetagem(model.getPorcetagem());
        dto.setQuantidadeDeProdutos((long) model.getItensPromocaos().size());

    }
}
