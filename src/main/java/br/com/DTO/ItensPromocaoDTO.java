package br.com.DTO;

import br.com.Model.Produto;
import lombok.Data;

@Data
public class ItensPromocaoDTO {
    private Long quantidade;
    private ProdutoDTO produtos;
}
