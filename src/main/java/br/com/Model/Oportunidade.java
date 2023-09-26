package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Oportunidade extends EntidadeGenerica implements Serializable {

    private String nomeOportunidade;

    @ManyToOne
    private Funil funil;

    @ManyToOne
    private EtapaDoFunil etapaDoFunil;

    @ManyToOne
    private Fonte fonte;

    @ManyToOne
    private Campanha campanha;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Cliente cliente;

    private Double valor;

    @OneToMany(mappedBy = "oportunidade")
    @JsonIgnore
    private List<HistoricoDeEtapasFunil> historicoDeEtapasFunils = new ArrayList<>();
}
