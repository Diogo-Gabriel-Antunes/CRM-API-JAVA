package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class EtapaDoFunil extends EntidadeGenerica implements Serializable {

    private String etapa;
    private Integer nivel;

    private Boolean finalizacao;

    private Boolean ativo;

    @ManyToOne
    @JsonIgnore
    @JsonbTransient
    private Funil funil;
}
