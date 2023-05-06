package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

@Getter
@Setter
@Entity
public class Fonte extends EntidadeGenerica {

    private String fonte;
    private Boolean ativo;
}
