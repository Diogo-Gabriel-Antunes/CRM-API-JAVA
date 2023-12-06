package br.com.Repository;

import br.com.Model.MateriaPrima;
import br.com.Security.DTO.UsuarioLogado;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.SQL.SQLCreator;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.List;

@ApplicationScoped
public class MateriaPrimaRepository extends Repository {

    public List<MateriaPrima> findAll(Integer offset, Integer limit, String nome) {
        SQLCreator sql = criaSql();
        sql.setOffset(offset);
        sql.setOrderBy("m.dataIntegracao");
        sql.setLimit(limit);

        sql.select("m","MateriaPrima")
                .from("MateriaPrima m ")
                .from("LEFT JOIN m.usuario u");

        if(StringUtil.stringValida(nome)){
            sql.where("m.nome LIKE :nome")
                    .paramLike("nome",nome);
        }

        return sql.listResult(MateriaPrima.class);
    }

    public MateriaPrima findByUuid(String uuid) {
        return em.createQuery("SELECT m FROM MateriaPrima m " +
                "LEFT JOIN m.usuario u " +
                "WHERE u.uuid = :uuidUsuario " +
                "AND m.uuid = :uuid",MateriaPrima.class)
                .setParameter("uuidUsuario", UsuarioLogado.getUsuario().getUuid())
                .setParameter("uuid",uuid)
                .getSingleResult();
    }
}
