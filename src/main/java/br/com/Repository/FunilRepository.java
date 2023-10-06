package br.com.Repository;

import br.com.Model.Fonte;
import br.com.Model.Funil;
import br.com.Security.DTO.UsuarioLogado;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FunilRepository extends Repository {
    public List<Funil> findAll() {
        return em.createQuery("SELECT f FROM Funil f " +
                        "LEFT JOIN f.usuario u " +
                        "WHERE u.uuid = :uuidUsuario " +
                        "AND f.ativo = true", Funil.class)
                .setParameter("uuidUsuario", UsuarioLogado.getUsuario().getUuid())
                .getResultList();


    }

    public Funil findByUuid(String uuid) {
        return em.createQuery("SELECT f FROM Funil f " +
                        "LEFT JOIN f.usuario u " +
                        "WHERE f.uuid = :funilUuid AND " +
                        "u.uuid = :usuarioUuid ", Funil.class)
                .setParameter("funilUuid", uuid)
                .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                .getSingleResult();
    }

    public Funil findPadrao() {
        try {
            return em.createQuery("SELECT f FROM Funil f " +
                            "LEFT JOIN f.usuario u " +
                            "WHERE u.uuid = :usuarioUuid " +
                            "AND f.padrao = true", Funil.class)
                    .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                    .getSingleResult();
        } catch (Throwable t) {
            return null;
        }
    }

    public List<Funil> findAll(Integer offset) {
        try {
            return em.createQuery("SELECT f FROM Funil f "
                            + "LEFT JOIN f.campanha c "
                            + "LEFT JOIN f.captacaos cap "
                            + "LEFT JOIN f.etapaDoFunils e"
                            + " ORDER BY f.dataIntegracao ASC " +
                            "OFFSET :offset")
                    .setParameter("offset", offset)
                    .getResultList();
        } catch (Throwable t) {
            return null;
        }
    }

    public List<Funil> findAllSoAtivo(Integer offset) {
        try {
            return em.createQuery("SELECT f FROM Funil f "
                            + "LEFT JOIN f.campanha c "
                            + "LEFT JOIN f.captacaos cap "
                            + "LEFT JOIN f.etapaDoFunils e" +
                            " WHERE f.ativo = :ativo"
                            + " ORDER BY f.dataIntegracao ASC " +
                            "OFFSET :offset")
                    .setParameter("offset", offset)
                    .setParameter("ativo", true)
                    .getResultList();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
}
