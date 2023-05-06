package br.com.DTO;

import br.com.Model.*;
import br.com.Model.Enum.DiaDaSemana;
import br.com.Model.Enum.DiaDoMes;
import br.com.Model.Enum.Horario;
import br.com.Model.Enum.Mes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CompromissoDTO {
    private String uuid;
    private LocalDateTime inicioCompromisso;
    private LocalDateTime fimCompromisso;
    @Enumerated(EnumType.STRING)
    private DiaDaSemana diaDaSemana;
    @Enumerated(EnumType.STRING)
    private Mes mes;
    @Enumerated(EnumType.STRING)
    private Horario horario;
    @Enumerated(EnumType.STRING)
    private Compromisso.TipoCompromisso tipoCompromisso;
    @Enumerated(EnumType.STRING)
    private DiaDoMes diaDoMes;
    @ManyToOne
    private OportunidadeDTO oportunidades;
    @ManyToOne
    private TarefaDTO tarefas;

}
