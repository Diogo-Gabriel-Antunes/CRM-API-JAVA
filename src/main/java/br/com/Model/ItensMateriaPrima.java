package br.com.Model;


import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class ItensMateriaPrima extends EntidadeGenerica {

    @ManyToOne
    private MateriaPrima materiaPrimas;
    private Long quantidadeUtiliza;
}
