package br.com.Repository;

import br.com.Model.Tarefa;
import br.com.Security.DTO.UsuarioLogado;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TarefaRepository extends Repository {

    public Tarefa findByUuid(String uuid) {
        try {
            return em.createQuery("SELECT t FROM Tarefa t " +
                            "LEFT JOIN t.usuario u " +
                            "WHERE u.uuid = :usuarioUuid AND t.uuid = :tarefaUuid ", Tarefa.class)
                    .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                    .setParameter("tarefaUuid", uuid)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Tarefa> findAll() {
        try {
            return em.createQuery("SELECT t FROM Tarefa t " +
                            "LEFT JOIN t.usuario u " +
                            "WHERE u.uuid = :usuarioUuid ", Tarefa.class)
                    .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                    .getResultList();
        } catch (Throwable t) {
            return null;
        }
    }
}
