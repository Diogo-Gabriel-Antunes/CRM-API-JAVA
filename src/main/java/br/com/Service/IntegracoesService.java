package br.com.Service;

import br.com.Model.Integracoes;
import br.com.Repository.IntegracoesRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class IntegracoesService {

    @Inject
    IntegracoesRepository integracoesRepository;


    @Transactional
    public void setupPadrao(){
        Integracoes email = integracoesRepository.getByIntegracao(true, false, false);
        Integracoes wpp = integracoesRepository.getByIntegracao(false, true, false);
        Integracoes googleMeet = integracoesRepository.getByIntegracao(false, false, true);


        if(email == null){
            Integracoes newEmail = new Integracoes();
            newEmail.setEmail(true);
            integracoesRepository.persist(newEmail);
        }

        if(wpp == null){
            Integracoes newWpp = new Integracoes();
            newWpp.setWhatsapp(true);
            integracoesRepository.persist(newWpp);
        }

        if(googleMeet == null){
            Integracoes newGoogleMeet = new Integracoes();
            newGoogleMeet.setGoogleMeet(true);
            integracoesRepository.persist(newGoogleMeet);
        }

    }
}
