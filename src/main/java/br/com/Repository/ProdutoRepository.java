package br.com.Repository;

import br.com.Model.ItensMateriaPrima;
import br.com.Model.MateriaPrima;
import br.com.Model.Produto;
import br.com.Security.DTO.UsuarioLogado;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.SQL.SQLCreator;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProdutoRepository extends Repository {


    public List<Produto> listAll(Integer offset) {
        SQLCreator sql  = criaSql();
        sql.setOffset(offset);
        sql.setOrderBy("p.dataIntegracao ASC");
        sql.select("p", "Produto")
                .from("Produto p ")
                .from("LEFT JOIN p.usuario u");


        return sql.listResult(Produto.class);

    }

    public Produto findByUuid(String uuid) {
        return em.createQuery("SELECT p FROM Produto p " +
                "LEFT JOIN p.usuario u " +
                " WHERE u.uuid = :usuarioUuid " +
                " AND p.uuid = :uuid ",Produto.class)
                .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public List<ItensMateriaPrima> getMateriaPrimaByUuid(String uuid) {
        try{
            return em.createQuery("SELECT i FROM Produto p " +
                    "LEFT JOIN p.itensMateriaPrimas i " +
                    "LEFT JOIN p.usuario u " +
                    "WHERE u.uuid = :uuidUsuario " +
                    "AND p.uuid = :uuid", ItensMateriaPrima.class)
                    .setParameter("uuidUsuario",UsuarioLogado.getUsuario().getUuid())
                    .setParameter("uuid",uuid)
                    .getResultList();
        }catch (Throwable t){
            return new ArrayList<>();
        }
    }
}
