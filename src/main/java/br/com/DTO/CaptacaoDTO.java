package br.com.DTO;

import br.com.Model.Campanha;
import br.com.Model.Cliente;
import br.com.Model.Funil;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CaptacaoDTO {
    private String uuid;
    private List<ClienteDTO> cliente;
    private FunilDTO funil;
    private CampanhaDTO campanha;
}
