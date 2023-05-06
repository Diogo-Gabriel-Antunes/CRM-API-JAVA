package br.com.Invokers.FuncIVK;

import br.com.Model.Campanha;
import br.com.Model.EtapaDoFunil;
import br.com.Model.Fonte;
import br.com.Model.Funil;
import org.acme.Util.InterfacesUtil.Invoker;

public class SelectIVK implements Invoker {

    private Campanha campanha;
    private br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO;

    public SelectIVK(Campanha campanha, br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO) {
        this.campanha = campanha;
        this.selectIVKDTO = selectIVKDTO;
    }

    public void invoker(){
        selectIVKDTO.setLabel(campanha.getCampanha());
        selectIVKDTO.setValue(campanha.getUuid());
    }

    public static class SelectIVKFonte implements Invoker{
        private Fonte fonte;
        private br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO;

        public SelectIVKFonte(Fonte fonte, br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO) {
            this.fonte = fonte;
            this.selectIVKDTO = selectIVKDTO;
        }

        public void invoker(){
            selectIVKDTO.setLabel(fonte.getFonte());
            selectIVKDTO.setValue(fonte.getUuid());
        }
    }
    public static class SelectIVKFunil implements Invoker{
        private Funil funil;
        private br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO;

        public SelectIVKFunil(Funil funil, br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO) {
            this.funil = funil;
            this.selectIVKDTO = selectIVKDTO;
        }

        public void invoker(){
            selectIVKDTO.setLabel(funil.getNomeFunil());
            selectIVKDTO.setValue(funil.getUuid());
        }
    }
    public static class SelectIVKEtapaFunil implements Invoker{
        private EtapaDoFunil etapaDoFunil;
        private br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO;

        public SelectIVKEtapaFunil(EtapaDoFunil etapaDoFunil, br.com.Invokers.IVK.SelectIVKDTO selectIVKDTO) {
            this.etapaDoFunil = etapaDoFunil;
            this.selectIVKDTO = selectIVKDTO;
        }

        public void invoker(){
            selectIVKDTO.setLabel(etapaDoFunil.getEtapa());
            selectIVKDTO.setValue(etapaDoFunil.getUuid());
        }
    }
}
