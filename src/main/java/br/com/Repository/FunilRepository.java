package br.com.Repository;

import br.com.Model.Funil;
import br.com.Security.DTO.UsuarioLogado;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.SQL.SQLCreator;
import org.acme.Util.PrimitiveUtil.BooleanUtils;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.List;

@ApplicationScoped
public class FunilRepository extends Repository {
    public List<Funil> findAll(Integer offset, Boolean soAtivo, String nomeFunil) {
        SQLCreator sql = criaSql();
        sql.setOffset(offset);
        sql.setOrderBy("f.dataIntegracao");


        sql.select("f" , "funil")
                .from("Funil f")
                .from("LEFT JOIN f.usuario u");



        if(BooleanUtils.isTrue(soAtivo)){
            sql.where("f.ativo = :ativo")
                    .param("ativo",soAtivo);
        }

        if(StringUtil.stringValida(nomeFunil)){
            sql.where("f.nomeFunil = :nomeFunil")
                    .param("nomeFunil",nomeFunil);
        }

        return sql.listResult(Funil.class);


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




}
