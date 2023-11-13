package br.com.Invokers.IVK;

import br.com.DTO.OportunidadeDTO;
import br.com.DTO.TarefaDTO;
import br.com.Model.Compromisso;
import br.com.Model.Enum.DiaDaSemana;
import br.com.Model.Enum.DiaDoMes;
import br.com.Model.Enum.Horario;
import br.com.Model.Enum.Mes;
import br.com.Model.Tarefa;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompromissoRepresentacaoIVK {

    private LocalDateTime inicioCompromisso;
    private LocalDateTime fimCompromisso;
    private DiaDaSemana diaDaSemana;
    private Mes mes;
    private Horario horario;
    private Compromisso.TipoCompromisso tipoCompromisso;
    private DiaDoMes diaDoMes;
    private OportunidadeDTO oportunidadeDTO;
    private TarefaDTO tarefaDTO;

}
