package br.com.DTO.Calendario;

import br.com.Model.Compromisso;
import br.com.Model.Enum.DiaDoMes;
import br.com.Model.Enum.Horario;
import br.com.Model.Enum.Mes;
import br.com.Model.Oportunidade;
import br.com.Model.Tarefa;
import lombok.Getter;
import lombok.Setter;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CalendarioDTO {

    private List<CalendarioDia> dias = new ArrayList<>();
    private Mes mes;

    @Getter
    @Setter
    public static class CalendarioHorario{
        private Horario horario;
        private Compromisso compromisso;
    }
    @Getter
    @Setter
    public static class CalendarioDia{
        private List<CalendarioHorario> calendarioHorarios = new ArrayList<>();
        private DiaDoMes diaDoMes;
    }



}

