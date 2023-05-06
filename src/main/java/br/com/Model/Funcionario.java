package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Funcionario extends EntidadeGenerica {

    private String nome;
    private String sobrenome;
    private LocalDate dataDeNascimento;
    @OneToOne
    private Endereco endereco;
    private Boolean ativo;
    private Boolean gerenciamento;
}
