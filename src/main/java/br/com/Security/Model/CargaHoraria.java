package br.com.Security.Model;

import br.com.Model.AA.EntidadeGenerica;
import br.com.Model.Enum.DiaDaSemana;
import br.com.Model.Enum.Horario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class CargaHoraria extends EntidadeGenerica implements Serializable {

    @Enumerated(EnumType.STRING)
    private DiaDaSemana diaDaSemana;
    private Horario horarioEntrada;
    private Horario horarioSaida;
    @ManyToOne
    @JsonbTransient
    private Configuracao configuracao;

}
