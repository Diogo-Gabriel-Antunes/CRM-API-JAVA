package br.com.Service;

import br.com.Model.AnotacoesLead;
import br.com.Model.Lead;
import br.com.Repository.LeadRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AnotacoesLeadService extends Service {

    @Inject
    private LeadRepository leadRepository;

    public Response createByAnotacao(String leadUuid, String json) {
        Lead lead = leadRepository.findByUuid(leadUuid);
        AnotacoesLead anotacoesLead = gson.fromJson(json, AnotacoesLead.class);
        em.persist(anotacoesLead);
        lead.getAnotacoes().add(anotacoesLead);
        em.persist(lead);
        return Response.ok(lead).build();
    }
}
