package br.com.Repository;

import br.com.Model.Campanha;
import br.com.Security.DTO.UsuarioLogado;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CampanhaRepository extends Repository {
    public List<Campanha> findAll(Integer offset, boolean ativo) {
        return em.createQuery("SELECT c FROM Campanha c " +
                        "LEFT JOIN c.usuario u " +
                        "WHERE u.uuid = :uuidUsuario " +
                        "AND c.ativa = :ativo" +
                        " ORDER BY c.dataIntegracao ASC" +
                        " OFFSET :offset", Campanha.class)
                .setParameter("uuidUsuario", UsuarioLogado.getUsuario().getUuid())
                .setParameter("ativo", ativo)
                .setParameter("offset", offset)
                .getResultList();


    }

    public List<Campanha> findAll(Integer offset) {
        return em.createQuery("SELECT c FROM Campanha c " +
                        "LEFT JOIN c.usuario u " +
                        "WHERE u.uuid = :uuidUsuario " +
                        " ORDER BY c.dataIntegracao ASC" +
                        " OFFSET :offset", Campanha.class)
                .setParameter("uuidUsuario", UsuarioLogado.getUsuario().getUuid())
                .setParameter("offset", offset)
                .getResultList();


    }


    public List<Campanha> findAll(boolean ativo) {
        return em.createQuery("SELECT c FROM Campanha c " +
                        "LEFT JOIN c.usuario u " +
                        "WHERE u.uuid = :uuidUsuario " +
                        "AND c.ativa = :ativo" +
                        " ", Campanha.class)
                .setParameter("uuidUsuario", UsuarioLogado.getUsuario().getUuid())
                .setParameter("ativo", ativo)
                .getResultList();
    }

    public Campanha findByUuid(String uuid) {
        return em.createQuery("SELECT c FROM Campanha c " +
                        " LEFT JOIN c.usuario u " +
                        " WHERE u.uuid = :uuidUsuario " +
                        " AND c.uuid = :uuid ", Campanha.class)
                .setParameter("uuid", uuid)
                .setParameter("uuidUsuario", UsuarioLogado.getUsuario().getUuid())
                .getSingleResult();
    }
}
