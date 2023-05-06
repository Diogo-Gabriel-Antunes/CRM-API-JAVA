package br.com.DTO;

import br.com.Model.Proposta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PropostaDTO {
    private String uuid;
    private Proposta.StatusDaProposta statusDaProposta;
    private LocalDateTime dataProposta;
    private Double orcamento;
    private ClienteDTO cliente;
    private EmpresaDTO empresa;
}
