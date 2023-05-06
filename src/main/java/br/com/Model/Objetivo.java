package br.com.Model;


import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

@Getter
@Setter
@Entity
public class Objetivo extends EntidadeGenerica {

    private String objetivo;
}
