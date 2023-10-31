package br.com.Service;

import br.com.DTO.Calendario.CalendarioDTO;
import br.com.Model.Enum.Mes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;

@ApplicationScoped
public class AgendaService extends Service {
    @Inject
    CalendarioService calendarioService;

    public Response getAgenda(Mes mes) {
        if (mes == null) {
            LocalDate hoje = LocalDate.now();
            for (Mes value : Mes.values()) {
                if (value.getMonth().ordinal()  == hoje.getMonth().ordinal()) {
                    mes = value;
                }
            }
        }

        CalendarioDTO calendario = calendarioService.createCalendario(mes);
        return Response.ok(calendario).build();
    }
}
