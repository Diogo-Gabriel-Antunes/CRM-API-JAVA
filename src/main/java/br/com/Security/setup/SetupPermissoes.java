package br.com.Security.setup;

import br.com.Security.Model.Modulo;
import br.com.Security.Model.Rota;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.SQL.SQLCreator;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public abstract class SetupPermissoes {
    @Inject
    EntityManager em;


//    public void modulo(String modulo) {
//        Modulo newModulo = new Modulo();
//        newModulo.setModulo(modulo);
//        SQLCreator sqlCreator = new SQLCreator();
//        sqlCreator.setEm(em);
//        sqlCreator.select("m.modulo", "Modulo")
//                .from("Modulo m")
//                .where("m.modulo = :modulo")
//                .param("modulo", modulo);
//
//
//        if (modulos.isEmpty()) {
//            em.persist(newModulo);
//        }
//    }

//    public void setup(String modulo,String... allRotas) {
//        List<String> rotas = Arrays.stream(allRotas).toList();
//        for (String rota : rotas) {
//            SQLCreator sqlCreator = new SQLCreator();
//            sqlCreator.select("r","rota")
//                    .from("Rota r")
//                    .from("LEFT JOIN r.modulo m")
//                    .where("m.modulo = :modulo")
//                    .where("r.slug = :slug")
//                    .param("modulo",modulo)
//                    .param("slug",rota);
//            List result = sqlCreator.executar();
//
//            if(result.isEmpty()){
//                Rota rota1 = new Rota();
//                rota1.setSlug(rota);
//                em.persist(rota1);
//            }
//        }
//    }
}
