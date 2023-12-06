package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Pedido extends EntidadeGenerica {

    @OneToMany
    private List<Itens> itens;
    private Double valorTotal;

    @ManyToOne
    private Cliente cliente;
}
