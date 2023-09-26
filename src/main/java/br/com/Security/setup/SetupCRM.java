package br.com.Security.setup;

import br.com.Security.Model.Modulo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.SQL.SQLCreator;

import java.util.List;

@ApplicationScoped
public class SetupCRM extends SetupPermissoes {
    @Inject
    EntityManager em;

    public SetupCRM() {
//        modulo("CRM");
    }


}
