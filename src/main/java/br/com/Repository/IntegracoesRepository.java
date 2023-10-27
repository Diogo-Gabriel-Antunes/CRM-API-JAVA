package br.com.Repository;

import br.com.DTO.ClienteModalDTO;
import br.com.Model.*;
import br.com.Security.DTO.UsuarioLogado;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.acme.Util.PrimitiveUtil.BooleanUtils;

import java.util.List;

@ApplicationScoped
public class IntegracoesRepository implements PanacheRepositoryBase<Integracoes, String> {

    private EntityManager em = this.getEntityManager();

    public Integracoes getByIntegracao(boolean email,boolean whatsapp,boolean googleMeet) {
        try{
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT I FROM Integracoes I LEFT JOIN I.usuario u WHERE u.uuid = :uuidUsuario");

            if(BooleanUtils.isTrue(email)){
                sb.append(" AND I.email = :email");

            }

            if(BooleanUtils.isTrue(whatsapp)){
                sb.append(" AND I.whatsapp = :whatsapp");
            }

            if(BooleanUtils.isTrue(googleMeet)){
                sb.append(" AND I.googleMeet = :googleMeet");
            }

            TypedQuery<Integracoes> query = em.createQuery(sb.toString(), Integracoes.class);

            if(BooleanUtils.isTrue(email)){
                query.setParameter("email", email);

            }

            if(BooleanUtils.isTrue(whatsapp)){
                query.setParameter("whatsapp",whatsapp);
            }

            if(BooleanUtils.isTrue(googleMeet)){
                query.setParameter("googleMeet",googleMeet);
            }

            query.setParameter("uuidUsuario",UsuarioLogado.getUsuario().getUuid());

            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
