package br.com.Invokers.FuncIVK;

import br.com.DTO.CampanhaDTO;
import br.com.DTO.EtapaDoFunilDTO;
import br.com.Invokers.IVK.FunilRepresentacaoIVK;
import br.com.Model.Funil;
import org.acme.Util.InterfacesUtil.Invoker;
import org.acme.Util.PrimitiveUtil.ArrayUtil;
import org.acme.Util.PrimitiveUtil.BooleanUtils;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class FuncFunilRepresentacaoIVK implements Invoker {

    private Funil model;

    private FunilRepresentacaoIVK dto;


    public FuncFunilRepresentacaoIVK(Funil model, FunilRepresentacaoIVK dto) {
        this.model = model;
        this.dto = dto;
    }

    public void invoker(){
        dto.setNomeFunil(model.getNomeFunil());
        if(BooleanUtils.isTrue(model.getAtivo())){
            dto.setAtivo(model.getAtivo());
        }
        if(BooleanUtils.isTrue(model.getPadrao())){
            dto.setPadrao(model.getPadrao());
        }
        if(model.getCampanha() != null){
            dto.setCampanha(new CampanhaDTO());
            dto.getCampanha().setUuid(model.getCampanha().getUuid());
        }

        if(StringUtil.stringValida(model.getListaIntegracoes())){
            dto.setListaIntegracoes(model.getListaIntegracoes());
        }

        if(ArrayUtil.validaArray(model.getEtapaDoFunils())){
            List<String> listUuidEtapas = model.getEtapaDoFunils().stream().map(etapa -> etapa.getUuid()).toList();
            dto.setEtapaDoFunil(new ArrayList<>());
            listUuidEtapas.forEach(uuid->{
                EtapaDoFunilDTO etapaDoFunilDTO = new EtapaDoFunilDTO();
                etapaDoFunilDTO.setUuid(uuid);
                dto.getEtapaDoFunil().add(etapaDoFunilDTO);
            });
        }
    }
}
