package br.com.Repository;

import br.com.Security.DTO.UsuarioLogado;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.SQL.SQLCreator;

@ApplicationScoped
public class Repository {

    @Inject
    protected EntityManager em;


    public SQLCreator criaSql(){
        SQLCreator sql = new SQLCreator();
        sql.setEm(em);
        sql.where("u.uuid = :uuidUsuario")
                .param("uuidUsuario", UsuarioLogado.getUsuario().getUuid());
        return sql;
    }
}
