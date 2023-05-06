package br.com.Repository;

import br.com.DTO.CompromissoDTO;
import br.com.Model.Compromisso;
import br.com.Model.Enum.Mes;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CalendarioRepository extends Repository {


    public List<Compromisso> getCompromisso(Mes mes){
        return em.createQuery("SELECT c FROM Compromisso c LEFT JOIN c.oportunidades o " +
                        "LEFT JOIN c.tarefas t WHERE c.mes = :mes",Compromisso.class)
                .setParameter("mes",mes)
                .getResultList();

    }
}
