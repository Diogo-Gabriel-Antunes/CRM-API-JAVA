package br.com.Repository;

import br.com.Model.AnotacoesLead;
import br.com.Model.Lead;
import br.com.Security.DTO.UsuarioLogado;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class LeadRepository extends Repository{


    public List<Lead> findByFunil(String funilUuid) {
        return em.createQuery("SELECT l FROM Lead l " +
                "LEFT JOIN l.usuario u " +
                "LEFT JOIN l.funil f " +
                "WHERE u.uuid = :usuarioUuid " +
                "AND f.uuid = :funilUuid ",Lead.class)
                .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                .setParameter("funilUuid",funilUuid)
                .getResultList();
    }
    public List<Lead> findByFunilPadrao() {
        return em.createQuery("SELECT l FROM Lead l " +
                "LEFT JOIN l.usuario u " +
                "LEFT JOIN l.funil f " +
                "WHERE u.uuid = :usuarioUuid " +
                "AND f.padrao = true ",Lead.class)
                .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                .getResultList();
    }

    public Lead findByUuid(String uuid) {
        return em.createQuery("SELECT l FROM Lead l " +
                "LEFT JOIN l.funil f " +
                "LEFT JOIN l.usuario u " +
                "WHERE u.uuid = :usuarioUuid AND l.uuid = :leadUuid",Lead.class)
                .setParameter("usuarioUuid",UsuarioLogado.getUsuario().getUuid())
                .setParameter("leadUuid",uuid)
                .getSingleResult();
    }

    public Lead getAnotacoesByUuid(String uuid) {
        return em.createQuery("SELECT l FROM Lead l " +
                "LEFT JOIN l.usuario u " +
                "LEFT JOIN l.anotacoes a " +
                "WHERE l.uuid = :leadUuid " +
                "AND u.uuid = :usuarioUuid ",Lead.class)
                .setParameter("leadUuid",uuid)
                .setParameter("usuarioUuid",UsuarioLogado.getUsuario().getUuid())
                .getSingleResult();
    }
}
