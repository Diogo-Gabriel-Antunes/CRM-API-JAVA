package br.com.Invokers.FuncIVK;


import br.com.Invokers.IVK.CampanhaTableIVKDTO;
import br.com.Invokers.IVK.EtapaFunilTableIVKDTO;
import br.com.Model.Campanha;
import br.com.Model.EtapaDoFunil;
import org.acme.Util.InterfacesUtil.Invoker;
import org.acme.Util.LocalDateTimeUtil;
import org.acme.Util.PrimitiveUtil.BooleanUtils;

public class CampanhaTableIVK implements Invoker {

    Campanha campanha;
    CampanhaTableIVKDTO ivk;

    public CampanhaTableIVK(Campanha campanha, CampanhaTableIVKDTO ivk) {
        this.campanha = campanha;
        this.ivk = ivk;
    }

    public void invoker() {
        ivk.setUuid(campanha.getUuid());
        ivk.setCampanha(campanha.getNomeCampanha());
        if(BooleanUtils.isTrue(campanha.getAtiva())){
            ivk.setStatus("Ativo");
        }else{
            ivk.setStatus("Desativo");
        }
        ivk.setDataCriacao(LocalDateTimeUtil.retornaYYYYMMDDTHHMMSS(campanha.getDataIntegracao()));
    }
}
