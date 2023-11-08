package br.com.Security.Repository;

import br.com.Repository.Repository;
import br.com.Security.DTO.UsuarioLogado;
import br.com.Security.Model.Configuracao;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConfiguracaoRepository extends Repository {

    public Configuracao getConfiguracao() {
        try {
            return em.createQuery("SELECT c FROM Configuracao c " +
                            "WHERE c.usuario.uuid = :usuarioUuid ", Configuracao.class)
                    .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
