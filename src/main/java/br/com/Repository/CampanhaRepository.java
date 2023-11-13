package br.com.Repository;

import br.com.Model.Campanha;
import br.com.Security.DTO.UsuarioLogado;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.SQL.SQLCreator;
import org.acme.Util.PrimitiveUtil.BooleanUtils;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.List;

@ApplicationScoped
public class CampanhaRepository extends Repository {
    public List<Campanha> findAll(Integer offset, boolean ativo, String nomeCampanha) {
        SQLCreator sqlCreator = criaSql();
        sqlCreator.setOffset(offset);

        sqlCreator.setOrderBy("c.dataIntegracao");


        sqlCreator.select("c", "Campanha")
                .from(" Campanha c ")
                .from("LEFT JOIN c.usuario u");



        if (BooleanUtils.isTrue(ativo)) {
            sqlCreator.where("c.ativa = :ativo")
                    .param("ativo", ativo);
        }

        if (StringUtil.stringValida(nomeCampanha)) {
            sqlCreator.where("c.nomeCampanha LIKE :nomeCampanha")
                    .paramLike("nomeCampanha", nomeCampanha);
        }

        return sqlCreator.listResult(Campanha.class);

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
