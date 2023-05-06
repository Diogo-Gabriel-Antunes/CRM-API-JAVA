package br.com.Resource;

import br.com.Model.Enum.Mes;
import br.com.Service.AgendaService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("agenda")
public class AgendaResource {

    @Inject
    private AgendaService agendaService;
    @GET
    public Response getAgenda(@QueryParam("mes") Mes mes) {
        return agendaService.getAgenda(mes);
    }
}
