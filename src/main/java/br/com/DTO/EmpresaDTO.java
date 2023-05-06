package br.com.DTO;

import br.com.Model.Contato;
import br.com.Model.Endereco;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.OneToOne;

@Getter
@Setter
public class EmpresaDTO {
    private String uuid;

    private String nome;
    private EnderecoDTO endereco;
    private ContatoDTO contato;
    private String setorAtividade;
    private Long numeroFuncionarios;
    private Double minFaturamento;
    private Double maxFaturamento;
}
