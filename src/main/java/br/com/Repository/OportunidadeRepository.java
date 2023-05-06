package br.com.Repository;

import br.com.DTO.OportunidadeTableDTO;
import br.com.Model.Oportunidade;
import br.com.Security.DTO.UsuarioLogado;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class OportunidadeRepository implements PanacheRepositoryBase<Oportunidade,String> {

    EntityManager em = this.getEntityManager();

    public Oportunidade findByUuid(String uuid){
       return em.createQuery("SELECT o FROM Oportunidade o LEFT JOIN o.usuario u " +
               "WHERE u.uuid = :usuarioUuid AND o.uuid = :uuid",Oportunidade.class)
               .setParameter("usuarioUuid",UsuarioLogado.getUsuario().getUuid())
               .setParameter("uuid",uuid)
               .getSingleResult();
    }

    public List<OportunidadeTableDTO> findAllByUser() {
        return em.createQuery("SELECT new br.com.DTO.OportunidadeTableDTO(o.uuid,o.nomeOportunidade ,c.nome,f.nomeFunil,edf.etapa,fo.fonte,ca.campanha) FROM Oportunidade o " +
                        "LEFT JOIN o.usuario u LEFT JOIN o.cliente c " +
                        "LEFT JOIN o.funil f LEFT JOIN o.etapaDoFunil edf " +
                        "LEFT JOIN o.fonte fo LEFT JOIN o.campanha ca " +
                        "WHERE u.uuid = :usuarioUuid",OportunidadeTableDTO.class)
                .setParameter("usuarioUuid",UsuarioLogado.getUsuario().getUuid())
                .getResultList();
    }

    public List<Oportunidade> findToTimeLine(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return em.createQuery("SELECT o FROM Oportunidade o LEFT JOIN o.usuario u" +
                " WHERE u.uuid = :usuarioUuid AND o.dataMarcada <= :dataInicio AND " +
                "o.dataMarcada >= :dataFim",Oportunidade.class)
                .setParameter("usuarioUuid",UsuarioLogado.getUsuario().getUuid())
                .setParameter("dataInicio",dataInicio)
                .setParameter("dataFim",dataFim)
                .getResultList();
    }
}
