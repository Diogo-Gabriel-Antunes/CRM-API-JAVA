package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

import java.util.List;

@Getter
@Setter
@Entity
public class Campanha extends EntidadeGenerica {

    private String campanha;

    private Boolean ativa;
    @OneToMany
    private List<Funcionario> funcionarios;
    @ManyToOne
    private Funcionario lider;
}
