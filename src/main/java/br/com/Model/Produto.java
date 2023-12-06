package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Produto extends EntidadeGenerica {

    private String nome;
    private Double preco;

    @ManyToMany
    private Set<ItensMateriaPrima> itensMateriaPrimas;
}
