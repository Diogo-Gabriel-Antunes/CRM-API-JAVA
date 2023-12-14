package br.com.DTO;

import br.com.Model.ItensPromocao;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
public class PromocaoDTO {
    private Double porcetagem;
    private String nomePromocao;
    private List<ItensPromocaoDTO> itensPromocaos;
}
