package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;


@Getter
@Entity
@Setter
public class Cliente extends EntidadeGenerica {

    private String nome;
    private String sobrenome;
    @OneToOne
    private Contato contato;
    @OneToOne
    private Endereco endereco;
    private String cargo;
    private String setor;
    @OneToOne
    private Empresa empresa;
    private Double minFaturamento;
    private Double maxFaturamento;
    @ManyToOne
    private Captacao captacao;
}
