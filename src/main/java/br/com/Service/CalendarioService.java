package br.com.Service;

import br.com.DTO.Calendario.CalendarioDTO;
import br.com.Model.Compromisso;
import br.com.Model.Enum.DiaDoMes;
import br.com.Model.Enum.Horario;
import br.com.Model.Enum.Mes;
import br.com.Repository.CalendarioRepository;
import br.com.Security.DTO.UsuarioLogado;
import br.com.Security.Model.CargaHoraria;
import org.acme.Util.PrimitiveUtil.BooleanUtils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class CalendarioService extends Service {

    @Inject
    CalendarioRepository calendarioRepository;

    public List<Horario> getHorariosPadrao() {
        return new ArrayList<>() {{
            add(Horario.H08);
            add(Horario.H09);
            add(Horario.H10);
            add(Horario.H11);
            add(Horario.H12);
            add(Horario.H13);
            add(Horario.H14);
            add(Horario.H15);
            add(Horario.H16);
            add(Horario.H17);
            add(Horario.H18);
        }};
    }

    public CalendarioDTO createCalendario(Mes mes) {
        CalendarioDTO calendarioDTO = new CalendarioDTO();
        List<Compromisso> compromissos = calendarioRepository.getCompromisso(mes);
        if (BooleanUtils.isTrue(mes.getTem31())) {
            createCalendario(calendarioDTO, compromissos, 31);
        } else {
            createCalendario(calendarioDTO, compromissos, 30);
        }

        ordernarHorarios(calendarioDTO);
        calendarioDTO.setMes(mes);
        return calendarioDTO;
    }

    private void ordernarHorarios(CalendarioDTO calendarioDTO) {
        Stream<CalendarioDTO.CalendarioDia> newDia = calendarioDTO.getDias().stream().map(dia -> {
            dia.getCalendarioHorarios().sort(Comparator.comparing(horario -> horario.getHorario().getValor())
            );

            return dia;
        });
        calendarioDTO.setDias(newDia.toList());
    }

    private void createCalendario(CalendarioDTO calendarioDTO, List<Compromisso> compromissos, Integer qtdDiasNoMes) {
        for (int i = 1; i <= qtdDiasNoMes; i++) {
            List<Compromisso> compromissosDoDia = new ArrayList<>();
            for (Compromisso compromisso : compromissos) {
                if (compromisso.getDiaDoMes().getDia() == i) {
                    compromissosDoDia.add(compromisso);
                }
            }
            List<CalendarioDTO.CalendarioHorario> calendarioHorario = createCalendarioHorario(compromissosDoDia);
            CalendarioDTO.CalendarioDia calendarioDia = new CalendarioDTO.CalendarioDia();
            calendarioDia.setCalendarioHorarios(calendarioHorario);
            for (DiaDoMes value : DiaDoMes.values()) {
                if (value.getDia() == i) {
                    calendarioDia.setDiaDoMes(value);
                }
            }
            calendarioDTO.getDias().add(calendarioDia);

        }
    }

    private List<CalendarioDTO.CalendarioHorario> createCalendarioHorario(List<Compromisso> compromisso) {
        List<CalendarioDTO.CalendarioHorario> calendarioHorarioList = new ArrayList<>();
        HashMap<Horario, Compromisso> horarioCompromisso = new HashMap();
        Boolean horarioPadrao = null;
        if (UsuarioLogado.getUsuario() != null && UsuarioLogado.getUsuario().getConfiguracao() != null) {
            horarioPadrao = UsuarioLogado.getUsuario().getConfiguracao().getHorarioPadrao();
        }
        if (BooleanUtils.isTrue(horarioPadrao) || UsuarioLogado.getUsuario().getConfiguracao() == null) {
            List<Horario> horariosPadrao = getHorariosPadrao();
            for (Horario horario : horariosPadrao) {
                horarioCompromisso.put(horario, new Compromisso());
            }
        } else {
            CargaHoraria cargaHoraria = UsuarioLogado.getUsuario().getConfiguracao().getCargaHoraria().get(0);
            Horario horarioEntrada = cargaHoraria.getHorarioEntrada();
            Horario horarioSaida = cargaHoraria.getHorarioSaida();
            for (Horario value : Horario.values()) {
                if (horarioEntrada.getValor() <= value.getValor() && horarioSaida.getValor() > value.getValor()) {
                    horarioCompromisso.put(value, new Compromisso());
                }
            }
        }

        for (Compromisso compromissoFor : compromisso) {
            Compromisso compromissoInHashMap = horarioCompromisso.get(compromissoFor.getHorario());
            if (compromissoInHashMap == null || compromissoInHashMap.getUuid() == null) {
                horarioCompromisso.replace(compromissoFor.getHorario(), compromissoFor);
            }
        }
        horarioCompromisso.forEach((key, value) -> {
            CalendarioDTO.CalendarioHorario calendarioHorario = new CalendarioDTO.CalendarioHorario();
            calendarioHorario.setHorario(key);
            calendarioHorario.setCompromisso(value);
            calendarioHorarioList.add(calendarioHorario);
        });
        return calendarioHorarioList;
    }
}
