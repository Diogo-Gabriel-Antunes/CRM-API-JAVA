package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class EtapaDoFunil extends EntidadeGenerica implements Serializable {

    private String etapa;

    @OneToMany(mappedBy = "etapaDoFunil")
    @JsonIgnore
    private List<HistoricoDeEtapasFunil> historicoDeEtapasFunils = new ArrayList<>();

    private Boolean ativo;

    @ManyToOne
    @JsonIgnore
    @JsonbTransient
    private Funil funil;
}
