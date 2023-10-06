package br.com.DTO;

import br.com.Model.Tarefa;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClienteAgendamentoDTO {

    private ClienteDTO cliente;
    private AgendamentoDTO agendamento;

    @Data
    public static class AgendamentoDTO{
        private TipoAgendamento tipoAgendamento;
        private LocalDateTime dataAgendamento;
        private Tarefa.TipoDeTarefa tipoDaTarefa;
        private String funilUuid;
        private String etapaDoFunilUuid;

    }

    public enum TipoAgendamento{
        TAREFA,OPORTUNIDADE;
    }
}
