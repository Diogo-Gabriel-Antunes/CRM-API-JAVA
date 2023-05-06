package br.com.Repository;

import br.com.Model.Campanha;
import br.com.Security.DTO.UsuarioLogado;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CampanhaRepository extends Repository{
    public List<Campanha> findAll() {
        return em.createQuery("SELECT c FROM Campanha c " +
                "LEFT JOIN c.usuario u " +
                "WHERE u.uuid = :uuidUsuario " +
                "AND c.ativa = true",Campanha.class)
                .setParameter("uuidUsuario", UsuarioLogado.getUsuario().getUuid())
                .getResultList();


    }
}
