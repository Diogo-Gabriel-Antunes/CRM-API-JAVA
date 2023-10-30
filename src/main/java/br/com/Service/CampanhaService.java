package br.com.Service;

import br.com.DTO.CampanhaDTO;
import br.com.Infra.Exceptions.Mensagem;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Invokers.FuncIVK.CampanhaTableIVK;
import br.com.Invokers.FuncIVK.SelectIVK;
import br.com.Invokers.IVK.CampanhaTableIVKDTO;
import br.com.Model.Campanha;
import br.com.Model.Funil;
import br.com.Repository.CampanhaRepository;

import br.com.Repository.FunilRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.BooleanUtils;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CampanhaService extends Service {

    @Inject
    private CampanhaRepository campanhaRepository;


    @Inject
    private FunilRepository funilRepository;


    public Response findAll(Integer offset, boolean ativo) {
        List<Campanha> campanhas = null;
        if(BooleanUtils.isTrue(ativo)){
            campanhas = campanhaRepository.findAll(offset,true);
        }else{
            campanhas = campanhaRepository.findAll(offset);
        }
        List<CampanhaTableIVKDTO> ivkdtos = new ArrayList<>();
        for (Campanha campanha : campanhas) {
            CampanhaTableIVKDTO campanhaTableIVKDTO = new CampanhaTableIVKDTO();
            fieldUtil.invokerExecutor(new CampanhaTableIVK(campanha,campanhaTableIVKDTO));
            ivkdtos.add(campanhaTableIVKDTO);
        }
        return Response.ok(ivkdtos).build();

    }

    public Response findBySelect() {
        List<Campanha> campanhas = campanhaRepository.findAll(true);
        List<br.com.Invokers.IVK.SelectIVKDTO> campanhaSelectIVKDTOS = new ArrayList<>();

        for (Campanha campanha : campanhas) {
            br.com.Invokers.IVK.SelectIVKDTO campanhaSelectIVKDTO = new br.com.Invokers.IVK.SelectIVKDTO();
            fieldUtil.invokerExecutor(new SelectIVK(campanha, campanhaSelectIVKDTO));
            campanhaSelectIVKDTOS.add(campanhaSelectIVKDTO);
        }
        return Response.ok(campanhaSelectIVKDTOS).build();
    }

    @Transactional
    public Response create(String json) {
        CampanhaDTO campanhaDTO = gson.fromJson(json,CampanhaDTO.class);
        validaDTO(campanhaDTO);
        Campanha campanha = montaDTO(campanhaDTO);
        campanha.setAtiva(true);
        em.persist(campanha);
        return  Response.ok(campanha).build();
    }

    private Campanha montaDTO(CampanhaDTO campanhaDTO) {
        Campanha campanha = new Campanha();

        campanha.setNomeCampanha(campanhaDTO.getCampanha());
        return campanha;
    }

    private static void validaDTO(CampanhaDTO campanhaDTO) {
        Validacoes validacoes = new Validacoes();
        if(StringUtil.stringValida(campanhaDTO.getCampanha())){
            validacoes.add("Nome campanha invalido", "Inseirir um nome valido");
        }
    }

    @Transactional
    public Response update(String uuid, String json) {
        Campanha campanha = campanhaRepository.findByUuid(uuid);

        if(campanha == null){
            return Response.status(404).build();
        }

        CampanhaDTO campanhaDTO = gson.fromJson(json, CampanhaDTO.class);
        validaDTO(campanhaDTO);

        campanha.setNomeCampanha(campanhaDTO.getCampanha());

        em.persist(campanha);

        return Response.ok().build();
    }

    public Response findOne(String uuid) {
        Campanha byUuid = campanhaRepository.findByUuid(uuid);
        if(byUuid != null){
            return Response.ok(byUuid).build();
        }else{
            return Response.noContent().build();
        }
    }

    @Transactional
    public Response delete(String uuid) {
        Campanha campanha = campanhaRepository.findByUuid(uuid);

        if(campanha != null){
            campanha.delete();
            return Response.ok().build();
        }else{
            return Response.status(404).build();
        }
    }

    @Transactional
    public Response updateStatus(String uuid) {
        Campanha campanha = campanhaRepository.findByUuid(uuid);

        if(campanha == null){
            return Response.status(404).build();
        }

         if(BooleanUtils.isTrue(campanha.getAtiva())){
             campanha.setAtiva(false);
         }else{
             campanha.setAtiva(true);
         }

         em.persist(campanha);

         return Response.ok().build();
    }
    @Transactional
    public Response vincularFunil(String uuid, String funilUuid) {
        Campanha campanha = campanhaRepository.findByUuid(uuid);
        Funil funil = funilRepository.findByUuid(funilUuid);

        validaCampanhaVincularFunil(campanha, funil);

        campanha.getFunil().add(funil);
        funil.setCampanha(campanha);

        em.persist(funil);
        em.persist(campanha);

        return Response.ok().build();
    }

    private void validaCampanhaVincularFunil(Campanha campanha, Funil funil) {
        Validacoes validacoes = new Validacoes();
        if(campanha == null ){
            validacoes.add("campanha invalida","");
        }

        if(funil == null){
            validacoes.add("funil invalido","");
        }
        validacoes.lancaErro();
    }
}
