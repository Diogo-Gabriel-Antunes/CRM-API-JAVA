package br.com.Repository;

import br.com.Model.Captacao;
import br.com.Security.DTO.UsuarioLogado;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CaptacaoRepository extends Repository {

    public Captacao findByFunil(String funilUuid) {
        try {
            return em.createQuery("SELECT c FROM Captacao c " +
                            "LEFT JOIN c.usuario u LEFT JOIN c.funil f " +
                            "WHERE u.uuid = :usuarioUuid AND f.uuid = :funilUuid", Captacao.class)
                    .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                    .setParameter("funilUuid", funilUuid)
                    .getSingleResult();
        } catch (Throwable t) {
            return null;
        }
    }

    public Captacao findByCampanha(String campanhaUuid) {
        try {

            return em.createQuery("SELECT c FROM Captacao c " +
                            "LEFT JOIN c.usuario u LEFT JOIN c.campanha ca " +
                            "WHERE u.uuid = :usuarioUuid AND ca.uuid = :campanhaUuid", Captacao.class)
                    .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                    .setParameter("campanhaUuid", campanhaUuid)
                    .getSingleResult();
        } catch (Throwable t) {
            return null;
        }
    }
}
