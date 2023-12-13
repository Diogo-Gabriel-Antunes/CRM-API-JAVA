package br.com.Repository;

import br.com.Model.Promocao;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.SQL.SQLCreator;

import java.util.List;

@ApplicationScoped
public class PromocaoRepository extends Repository {


    public List<Promocao> findAll(Integer offset, Integer limit) {
        SQLCreator sql = criaSql();
        sql.setLimit(limit);
        sql.setOffset(offset);
        sql.setOrderBy("p.dataIntegracao");
        sql.select("p","Promocao")
                .from("Promocao p")
                .from("LEFT JOIN p.usuario u");

        return sql.listResult(Promocao.class);
    }

    public Promocao findByUuid(String uuid) {
        return em.createQuery("SELECT p FROM Promocao p WHERE p.uuid = :uuid",Promocao.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }
}
