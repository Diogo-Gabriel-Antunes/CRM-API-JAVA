package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

import java.util.List;

@Getter
@Setter
@Entity
public class Campanha extends EntidadeGenerica {

    private String nomeCampanha;

    private Boolean ativa;

    @OneToMany(mappedBy = "campanha")
    private List<Funil> funil;
}
