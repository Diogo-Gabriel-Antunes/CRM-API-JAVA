package br.com.Invokers.FuncIVK;


import br.com.Invokers.IVK.EtapaFunilTableIVKDTO;
import br.com.Invokers.IVK.TarefasIVKDTO;
import br.com.Model.EtapaDoFunil;
import br.com.Model.Tarefa;
import org.acme.Util.InterfacesUtil.Invoker;
import org.acme.Util.LocalDateTimeUtil;
import org.acme.Util.PrimitiveUtil.BooleanUtils;

public class EtapaFunilTableIVK implements Invoker {

    EtapaDoFunil etapaDoFunil;
    EtapaFunilTableIVKDTO ivk;

    public EtapaFunilTableIVK(EtapaDoFunil etapaDoFunil, EtapaFunilTableIVKDTO ivk) {
        this.etapaDoFunil = etapaDoFunil;
        this.ivk = ivk;
    }

    public void invoker() {
        ivk.setUuid(etapaDoFunil.getUuid());
        ivk.setEtapa(etapaDoFunil.getEtapa());
        if(BooleanUtils.isTrue(etapaDoFunil.getAtivo())){
            ivk.setStatus("Ativo");
        }else{
            ivk.setStatus("Desativado");
        }

        if(etapaDoFunil.getDataAtualizacao() != null){
            ivk.setDataAtualizacao(LocalDateTimeUtil.retornaYYYYMMDDTHHMMSS(etapaDoFunil.getDataAtualizacao()));
        }
        if(etapaDoFunil.getDataIntegracao() != null){
            ivk.setDataIntegracao(LocalDateTimeUtil.retornaYYYYMMDDTHHMMSS(etapaDoFunil.getDataIntegracao()));
        }

    }
}
