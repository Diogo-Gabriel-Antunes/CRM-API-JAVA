package br.com.Service;

import br.com.DTO.FunilDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Invokers.FuncIVK.FuncFunilRepresentacaoIVK;
import br.com.Invokers.FuncIVK.FunilTableIVK;
import br.com.Invokers.FuncIVK.SelectIVK;
import br.com.Invokers.IVK.FunilRepresentacaoIVK;
import br.com.Invokers.IVK.FunilTableIVKDTO;
import br.com.Model.Funil;
import br.com.Model.Integracoes;
import br.com.Repository.FunilRepository;

import br.com.Repository.IntegracoesRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FunilService extends Service {

    @Inject
    private FunilRepository funilRepository;

    @Inject
    private IntegracoesService integracoesService;

    @Inject
    private IntegracoesRepository integracoesRepository;

    public Response findAll(Integer offset, Boolean soAtivo, String nomeFunil) {
        List<Funil> funils = funilRepository.findAll(offset,soAtivo,nomeFunil);
         List<FunilTableIVKDTO> ivk = new ArrayList<>();
        for (Funil funil : funils) {
            FunilTableIVKDTO funilTableIVKDTO = new FunilTableIVKDTO();
            fieldUtil.invokerExecutor(new FunilTableIVK(funil, funilTableIVKDTO));
            ivk.add(funilTableIVKDTO);
        }
        return Response.ok(ivk).build();

    }

    public Response findBySelect() {
        List<Funil> funils = funilRepository.findAll(0, null, null);
        List<br.com.Invokers.IVK.SelectIVKDTO> selectIVKDTOS = new ArrayList<>();

        for (Funil funil : funils) {
            br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO = new br.com.Invokers.IVK.SelectIVKDTO();
            fieldUtil.invokerExecutor(new SelectIVK.SelectIVKFunil(funil, selectIVKDTO));
            selectIVKDTOS.add(selectIVKDTO);
        }
        return Response.ok(selectIVKDTOS).build();
    }

    @Transactional
    public Response create(String json) {
        FunilDTO funilDTO = gson.fromJson(json, FunilDTO.class);
        validaDTO(funilDTO);
        Funil funil = montarFunil(funilDTO);
        em.persist(funil);
        return Response.ok(funil).build();
    }

    private Funil montarFunil(FunilDTO funilDTO) {
        Funil funil = new Funil();
        funil.setNomeFunil(funilDTO.getNomeFunil());
        Funil funilPadrao = funilRepository.findPadrao();
        if (funilPadrao == null) {
            funil.setPadrao(true);
        } else {
            funil.setPadrao(false);
        }
        funil.setAtivo(true);

        funil.setIntegracoes(montaIntegracoes(funilDTO));
        funil.setListaIntegracoes(montaLsIntegracoes(funilDTO));
        return funil;
    }

    private String montaLsIntegracoes(FunilDTO funilDTO) {
        String ls = "";
        for (String integracao : funilDTO.getIntegracoes()) {
            if (ls == "") {
                ls = integracao;
            } else {
                ls += "," + integracao;
            }
        }
        return ls;
    }

    private List<Integracoes> montaIntegracoes(FunilDTO funilDTO) {
        integracoesService.setupPadrao();
        List<Integracoes> integracoes = new ArrayList<Integracoes>();
        for (String integracoe : funilDTO.getIntegracoes()) {
            if (integracoe.equals("whatsapp")) {
                Integracoes newWpp = new Integracoes();
                newWpp.setWhatsapp(true);
                integracoesRepository.persist(newWpp);
                integracoes.add(newWpp);
            }

            if (integracoe.equals("email")) {
                Integracoes newEmail = new Integracoes();
                newEmail.setEmail(true);
                integracoesRepository.persist(newEmail);
                integracoes.add(newEmail);
            }

            if (integracoe.equals("google-meet")) {
                Integracoes newGoogleMeet = new Integracoes();
                newGoogleMeet.setGoogleMeet(true);
                integracoesRepository.persist(newGoogleMeet);
                integracoes.add(newGoogleMeet);
            }
        }
        return integracoes;
    }

    private void validaDTO(FunilDTO funilDTO) {
        Validacoes validacoes = new Validacoes();
        validaDTO(validacoes, funilDTO);
        validacoes.lancaErro();
    }

    public void validaDTO(Validacoes validacoes, FunilDTO funilDTO) {

        if (!StringUtil.stringValida(funilDTO.getNomeFunil())) {
            validacoes.add("Nome do funil Invalido", "Verifique se adicionou um nome valido");
        }

    }

    public Response getOne(String uuid) {
        Funil funil = funilRepository.findByUuid(uuid);

        if (funil == null) {
            return Response.noContent().build();
        } else {
            FunilRepresentacaoIVK funilRepresentacaoIVK = new FunilRepresentacaoIVK();
            fieldUtil.invokerExecutor(new FuncFunilRepresentacaoIVK(funil,funilRepresentacaoIVK));
            return Response.ok(funilRepresentacaoIVK).build();
        }
    }

    @Transactional
    public Response delete(String uuid) {
        Funil funil = funilRepository.findByUuid(uuid);

        if(funil == null) {
            return Response.status(404).build();
        }else{
            funil.delete();
            return Response.ok(funil).build();
        }
    }
}
