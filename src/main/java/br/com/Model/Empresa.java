package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
public class Empresa extends EntidadeGenerica {

    private String nome;
    @OneToOne
    private Endereco endereco;
    @OneToOne
    private Contato contato;
    private String setorAtividade;
    private Long numeroFuncionarios;
    private Double minFaturamento;
    private Double maxFaturamento;



}
