package br.com.Security.Model;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Configuracao extends EntidadeGenerica {

    @OneToMany(mappedBy = "configuracao",cascade = CascadeType.ALL)
    private List<CargaHoraria> cargaHoraria;
    private Boolean horarioPadrao;

}
