package br.com.Invokers.FuncIVK;

import br.com.Invokers.IVK.TableEtapaFunilIVK;
import br.com.Model.EtapaDoFunil;
import org.acme.Util.InterfacesUtil.Invoker;
import org.acme.Util.PrimitiveUtil.BooleanUtils;

public class FuncTableEtapaFunilIVK implements Invoker {


    private TableEtapaFunilIVK dto;
    private EtapaDoFunil model;

    public FuncTableEtapaFunilIVK(TableEtapaFunilIVK dto, EtapaDoFunil model) {
        this.dto = dto;
        this.model = model;
    }

    public void invoke(){
        dto.setUuid(model.getUuid());
        dto.setEtapa(model.getEtapa());

        if(BooleanUtils.isTrue(model.getFinalizacao())) {
            dto.setFinalizacao("Finalizacao");
        }else{
            dto.setFinalizacao("-");
        }

        if(BooleanUtils.isTrue(model.getAtivo())){
            dto.setAtivo("Ativo");
        }else{
            dto.setAtivo("Desativado");
        }

        dto.setNivel(model.getNivel());
    }
}
