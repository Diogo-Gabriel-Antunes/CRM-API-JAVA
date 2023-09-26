package br.com.Security.Model;

import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

@Getter
@Setter
@Entity
public class Rota extends EntidadeGenerica {

    private String nome;
    private String slug;
    @ManyToOne
    private Modulo modulo;
}
