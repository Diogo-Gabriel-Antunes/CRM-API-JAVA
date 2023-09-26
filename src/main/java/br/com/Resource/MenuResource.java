package br.com.Resource;


import br.com.Service.MenuService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("menu")
public class MenuResource {

    @Inject
    private MenuService menuService;
    @GET
    @Path("cliente")
    public Response getMenuCliente(){
        return menuService.getMenuCliente();
    }

    @GET
    @Path("agenda")
    public Response getMenuAgenda(){
        return menuService.getMenuAgenda();
    }

    @GET
    @Path("drag-drop")
    public Response getMenuDragDrop(){
        return menuService.getMenuDragDrop();
    }
}
