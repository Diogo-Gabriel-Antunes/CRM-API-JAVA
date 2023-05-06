package br.com.Repository;

import br.com.Model.Campanha;
import br.com.Model.Fonte;
import br.com.Security.DTO.UsuarioLogado;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FonteRepository extends Repository{
    public List<Fonte> findAll() {
        return em.createQuery("SELECT f FROM Fonte f " +
                        "LEFT JOIN f.usuario u " +
                        "WHERE u.uuid = :uuidUsuario " +
                        "AND f.ativo = true",Fonte.class)
                .setParameter("uuidUsuario", UsuarioLogado.getUsuario().getUuid())
                .getResultList();


    }
}
