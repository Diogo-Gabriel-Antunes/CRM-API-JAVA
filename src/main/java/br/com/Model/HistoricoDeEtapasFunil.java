package br.com.Model;


import br.com.Model.AA.EntidadeGenerica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class HistoricoDeEtapasFunil extends EntidadeGenerica implements Serializable {

    @ManyToOne
    @JsonIgnore
    private Oportunidade oportunidade;

    @ManyToOne

    private EtapaDoFunil etapaDoFunil;
    private LocalDateTime dataDaMudanca;
}
