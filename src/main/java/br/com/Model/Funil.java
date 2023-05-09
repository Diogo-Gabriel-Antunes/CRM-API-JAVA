package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Funil extends EntidadeGenerica {

    private String nomeFunil;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Integracoes> integracoes;
    private String listaIntegracoes;
    @ManyToOne
    private Campanha campanha;
    private Boolean ativo;
    private Boolean padrao;

    @OneToMany(mappedBy = "funil",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Captacao> captacaos = new ArrayList<>();


    @OneToMany(mappedBy = "funil",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<EtapaDoFunil> etapaDoFunils = new ArrayList<>();


}
