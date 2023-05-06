package br.com.Repository;

import br.com.Model.EtapaDoFunil;
import br.com.Model.Funil;
import br.com.Security.DTO.UsuarioLogado;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EtapaDoFunilRepository extends Repository{
    public List<EtapaDoFunil> findAll(String uuidFunil) {
        return em.createQuery("SELECT ef FROM EtapaDoFunil ef " +
                        "LEFT JOIN ef.usuario u " +
                        "LEFT JOIN ef.funil f " +
                        "WHERE u.uuid = :uuidUsuario " +
                        "AND f.uuid = :uuidFunil " +
                        "AND ef.ativo = true", EtapaDoFunil.class)
                .setParameter("uuidUsuario", UsuarioLogado.getUsuario().getUuid())
                .setParameter("uuidFunil",uuidFunil)
                .getResultList();


    }

    public List<EtapaDoFunil> findByFunil(String funilUuid) {
        return em.createQuery( "SELECT e FROM EtapaDoFunil e " +
                "LEFT JOIN e.usuario u " +
                "LEFT JOIN e.funil f " +
                "WHERE u.uuid = :usuarioUuid " +
                "AND f.uuid = :funilUuid ",EtapaDoFunil.class)
                .setParameter("usuarioUuid",UsuarioLogado.getUsuario().getUuid())
                .setParameter("funilUuid", funilUuid)
                .getResultList();
    }

    public EtapaDoFunil findByUuid(String uuid) {
        return em.createQuery("SELECT e FROM EtapaDoFunil e " +
                "LEFT JOIN e.usuario u " +
                "WHERE e.uuid = :etapaUuid AND " +
                "u.uuid = :usuarioUuid ",EtapaDoFunil.class)
                .setParameter("etapaUuid",uuid)
                .setParameter("usuarioUuid",UsuarioLogado.getUsuario().getUuid())
                .getSingleResult();
    }

    public List<EtapaDoFunil> findByFunilPadrao() {
        return em.createQuery("SELECT e FROM EtapaDoFunil e " +
                "LEFT JOIN e.usuario u " +
                "LEFT JOIN e.funil f " +
                "WHERE f.padrao = true " +
                "AND u.uuid = :usuarioUuid",EtapaDoFunil.class)
                .setParameter("usuarioUuid",UsuarioLogado.getUsuario().getUuid())
                .getResultList();
    }
}
