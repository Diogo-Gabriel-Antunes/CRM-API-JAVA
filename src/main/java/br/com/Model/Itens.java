package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Itens extends EntidadeGenerica {

    @OneToMany
    private List<Produto> produtos;

    private Integer quantidade;


}
