package br.com.Repository;

import br.com.Model.EtapaDoFunil;
import br.com.Model.Funil;
import br.com.Security.DTO.UsuarioLogado;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.SQL.SQLCreator;
import org.acme.Util.PrimitiveUtil.BooleanUtils;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.List;

@ApplicationScoped
public class EtapaDoFunilRepository extends Repository {
    public List<EtapaDoFunil> findAll(String uuidFunil, Integer offset, String etapa, Integer nivel, Boolean finalizacao) {
        SQLCreator sqlCreator = criaSql();
        sqlCreator.setLimit(20);
        sqlCreator.setOffset(offset);
        sqlCreator.setOrderBy("ef.dataIntegracao");
        sqlCreator.select(" ef ", "EtapaDoFunil")
                .from("EtapaDoFunil ef")
                .from(" LEFT JOIN ef.usuario u")
                .from(" LEFT JOIN ef.funil f");


        if(StringUtil.stringValida(uuidFunil)){
            sqlCreator.where("f.uuid = :funilUuid")
                    .param("funilUuid",uuidFunil);
        }

        if (StringUtil.stringValida(etapa)) {
            sqlCreator.where("UPPER(ef.etapa) LIKE UPPER(:etapa)")
                    .paramLike("etapa", etapa);
        }
        if (nivel != null) {
            sqlCreator.where("ef.nivel = :nivel")
                    .param("nivel", nivel);
        }

        if (BooleanUtils.isTrue(finalizacao)) {
            sqlCreator.where("ef.finalizacao = :finalizacao")
                    .param("finalizacao", finalizacao);
        }


        return sqlCreator.listResult(EtapaDoFunil.class);
    }

    public List<EtapaDoFunil> findAll(String uuidFunil) {
        return em.createQuery("SELECT ef FROM EtapaDoFunil ef " +
                        "LEFT JOIN ef.usuario u " +
                        "LEFT JOIN ef.funil f " +
                        "WHERE u.uuid = :uuidUsuario " +
                        "AND f.uuid = :uuidFunil ", EtapaDoFunil.class)
                .setParameter("uuidUsuario", UsuarioLogado.getUsuario().getUuid())
                .setParameter("uuidFunil", uuidFunil)
                .getResultList();


    }

    public List<EtapaDoFunil> findByFunil(String funilUuid) {
        return em.createQuery("SELECT e FROM EtapaDoFunil e " +
                        "LEFT JOIN e.usuario u " +
                        "LEFT JOIN e.funil f " +
                        "WHERE u.uuid = :usuarioUuid " +
                        "AND f.uuid = :funilUuid ", EtapaDoFunil.class)
                .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                .setParameter("funilUuid", funilUuid)
                .getResultList();
    }

    public EtapaDoFunil findByUuid(String uuid) {
        return em.createQuery("SELECT e FROM EtapaDoFunil e " +
                        "LEFT JOIN e.usuario u " +
                        "WHERE e.uuid = :etapaUuid AND " +
                        "u.uuid = :usuarioUuid ", EtapaDoFunil.class)
                .setParameter("etapaUuid", uuid)
                .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                .getSingleResult();
    }

    public List<EtapaDoFunil> findByFunilPadrao() {
        return em.createQuery("SELECT e FROM EtapaDoFunil e " +
                        "LEFT JOIN e.usuario u " +
                        "LEFT JOIN e.funil f " +
                        "WHERE f.padrao = true " +
                        "AND u.uuid = :usuarioUuid", EtapaDoFunil.class)
                .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                .getResultList();
    }

    public List<EtapaDoFunil> findAll() {
        return em.createQuery("SELECT e FROM EtapaDoFunil e " +
                        "LEFT JOIN e.usuario u " +
                        "WHERE u.uuid = :usuarioUuid", EtapaDoFunil.class)
                .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                .getResultList();
    }

    public EtapaDoFunil findByFunilAndName(Funil funil, String etapaName) {
        return em.createQuery("SELECT ef FROM EtapaDoFunil ef " +
                " LEFT JOIN ef.funil f " +
                " WHERE ef.etapa = :etapaName " +
                " AND f.uuid = :funilUuid ",EtapaDoFunil.class)
                .setParameter("etapaName",etapaName)
                .setParameter("funilUuid",funil.getUuid())
                .getSingleResult();

    }
}
