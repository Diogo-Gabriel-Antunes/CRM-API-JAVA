package br.com.Security.Repository;

import br.com.Security.Model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<Usuario,String> {

    public Usuario getByEmail(String email ){
        HashMap<String,Object> params = new HashMap<>();
        params.put("email",email);
        return Usuario.find("email = :email",params).firstResult();
    }
}
