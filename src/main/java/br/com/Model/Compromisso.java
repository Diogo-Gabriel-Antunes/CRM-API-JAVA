package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import br.com.Model.Enum.DiaDaSemana;
import br.com.Model.Enum.DiaDoMes;
import br.com.Model.Enum.Horario;
import br.com.Model.Enum.Mes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Compromisso extends EntidadeGenerica {

    private LocalDateTime inicioCompromisso;
    private LocalDateTime fimCompromisso;
    @Enumerated(EnumType.STRING)
    private DiaDaSemana diaDaSemana;
    @Enumerated(EnumType.STRING)
    private Mes mes;
    @Enumerated(EnumType.STRING)
    private Horario horario;
    @Enumerated(EnumType.STRING)
    private TipoCompromisso tipoCompromisso;
    @Enumerated(EnumType.STRING)
    private DiaDoMes diaDoMes;
    @ManyToOne(fetch = FetchType.LAZY)
    private Oportunidade oportunidades;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tarefa tarefas;
    @ManyToOne(fetch = FetchType.LAZY)
    private Integracoes integracao;

    public enum TipoCompromisso {
        OPORTUNIDADE, TAREFA;
    }
}
