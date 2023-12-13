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
            add(new MenuDTO("agenda/visualizar", "Visualizar"));
            add(new MenuDTO("agenda/configuracao", "Configurações"));
        }}).build();
    }

    public Response getMenuDragDrop() {
        return Response.ok().entity(new ArrayList<MenuDTO>() {{
            add(new MenuDTO("drag-drop/visualizar", "visualizar"));
            add(new MenuDTO("drag-drop/configuracoes", "configuracoes"));
        }}).build();
    }

    public Response getMenuProduto() {
        return Response.ok().entity(new ArrayList<MenuDTO>() {{
            add(new MenuDTO("produto/cadastrar", "cadastrar"));
            add(new MenuDTO("produto/materia-prima", "Mataria Prima"));
            add(new MenuDTO("produto/promocao", "Promocao"));
        }}).build();
    }

    public Response getMenuEmail() {
        return Response.ok().entity(new ArrayList<MenuDTO>() {{
            add(new MenuDTO("email/cadastrar", "Cadastrar Template"));
            add(new MenuDTO("email/enviar-email", "Enviar email"));
            add(new MenuDTO("email/caixa-postal", "Caixa Postal"));
            add(new MenuDTO("email/configuracoes", "Configuracoes"));
        }}).build();
        }
}
