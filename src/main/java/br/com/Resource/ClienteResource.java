package br.com.Resource;

import br.com.Model.Cliente;
import br.com.Repository.ClienteRepository;
import br.com.Service.ClienteService;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("cliente")
public class ClienteResource {

    @Inject
    private ClienteService clienteService;
    @Inject
    private ClienteRepository clienteRepository;


    @GET
    public Response listAll(@QueryParam("offset") Integer offset,
                            @QueryParam("nome")String nome,
                            @QueryParam("sobrenome")String sobrenome){
        List<Cliente> clientes = clienteRepository.findAllByUsuario(offset,nome,sobrenome);
        if(clientes.isEmpty()){
            return Response.noContent().build();
        }else{
            return Response.ok(clientes).build();
        }
    }

    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        Cliente cliente = Cliente.findById(uuid);
        if(cliente != null){
            return Response.ok(cliente).build();
        }else{
            return Response.status(404).build();
        }
    }

    @POST
    public Response create(String json){
        return clienteService.create(json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String json){
        return clienteService.update(uuid,json);
    }

    @GET
    @Path("modal")
    public Response getClientesModal(){
        return clienteService.clientesModal();
    }


    @DELETE
    @Path("{uuid}")
    public Response deleteClientes(@PathParam("uuid")String uuid){
        return clienteService.delete(uuid);
    }
}
