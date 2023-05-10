package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Lead extends EntidadeGenerica {

    private String descricao;
    private LocalDateTime data;

    @ManyToOne
    private Funil funil;
    @ManyToOne
    private EtapaDoFunil etapaDoFunil;
    @ManyToOne
    private Campanha campanha;
    private Double valor;
    @ManyToOne
    private Cliente cliente;
    @OneToMany(fetch = FetchType.EAGER)
    private List<AnotacoesLead> anotacoes ;


}
