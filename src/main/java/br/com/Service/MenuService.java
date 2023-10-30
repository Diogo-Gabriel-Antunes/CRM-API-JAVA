package br.com.Service;


import br.com.DTO.MenuDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@ApplicationScoped
public class MenuService {
    public Response getMenuCliente() {
        return Response.ok().entity(new ArrayList<MenuDTO>() {{
            add(new MenuDTO("cliente/cadastro", "Cadastro"));
            add(new MenuDTO("cliente/funil", "Funil"));
            add(new MenuDTO("cliente/campanha", "Campanha"));
            add(new MenuDTO("cliente/relatorio", "Relatorio"));
            add(new MenuDTO("cliente/cadastro-de-perfil", "Cadastro de perfil"));
        }}).build();
    }

    public Response getMenuAgenda() {
        return Response.ok().entity(new ArrayList<MenuDTO>() {{
            add(new MenuDTO("agenda/funil", "Funil"));
            add(new MenuDTO("agenda/campanha", "Campanha"));
            add(new MenuDTO("agenda/relatorio", "Relatorio"));
        }}).build();
    }

    public Response getMenuDragDrop() {
        return Response.ok().entity(new ArrayList<MenuDTO>() {{
            add(new MenuDTO("drag-drop/cadastro", "Cadastro"));
            add(new MenuDTO("drag-drop/funil", "Funil"));
            add(new MenuDTO("drag-drop/campanha", "Campanha"));
            add(new MenuDTO("drag-drop/relatorio", "Relatorio"));
        }}).build();
    }
}
