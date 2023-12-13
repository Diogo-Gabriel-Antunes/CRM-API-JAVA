package br.com.Model;


import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Promocao extends EntidadeGenerica {

    private Double porcetagem;

    @ManyToMany
    private List<ItensPromocao> itensPromocaos;
}
