package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MateriaPrima extends EntidadeGenerica {

    private String nome;
    private Long quantidadeEstoque;
}
