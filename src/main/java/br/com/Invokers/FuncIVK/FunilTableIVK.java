package br.com.Invokers.FuncIVK;

import br.com.Invokers.IVK.FunilTableIVKDTO;
import br.com.Model.Funil;
import br.com.Model.Integracoes;
import org.acme.Util.InterfacesUtil.Invoker;
import org.acme.Util.PrimitiveUtil.BooleanUtils;
import org.acme.Util.PrimitiveUtil.StringUtil;

public class FunilTableIVK implements Invoker {

    private Funil funil;
    private FunilTableIVKDTO dto;

    public FunilTableIVK(Funil funil, FunilTableIVKDTO dto) {
        this.funil = funil;
        this.dto = dto;
    }

    public void invoker() {
        dto.setUuid(funil.getUuid());
        dto.setNomeFunil(funil.getNomeFunil());

        for (Integracoes integracoe : funil.getIntegracoes()) {
            if (dto.getIntegracoes().isEmpty()) {
                if (BooleanUtils.isTrue(integracoe.getEmail())) {
                    dto.setIntegracoes("E-mail");
                }
                if (BooleanUtils.isTrue(integracoe.getWhatsapp())) {
                    dto.setIntegracoes(dto.getIntegracoes().isEmpty() ? "WhastApp" : ", WhatsApp");
                }
            } else {
                if (BooleanUtils.isTrue(integracoe.getEmail())) {
                    dto.setIntegracoes(", E-mail");
                }
                if (BooleanUtils.isTrue(integracoe.getWhatsapp())) {
                    dto.setIntegracoes(", WhatsApp");
                }
            }
        }
        if (funil.getPadrao() != null && funil.getPadrao()) {
            dto.setPadrao("Padrão");
        } else {
            dto.setPadrao("-");
        }
        if (funil.getEtapaDoFunils() != null) {
            dto.setQuantidadeEtapas(funil.getEtapaDoFunils().size());
        }
        if (funil.getCaptacaos() != null && !funil.getCaptacaos().isEmpty()) {
            dto.setQuantiadeCaptacoes(funil.getCaptacaos().size());
        } else {
            dto.setQuantiadeCaptacoes(0);
        }

        if(funil.getCampanha() != null){
            if(StringUtil.stringValida(funil.getCampanha().getNomeCampanha())){
                dto.setNomeCampanha(funil.getCampanha().getNomeCampanha());
            }else{
                dto.setNomeCampanha("Sem campanha");
            }
        }else{
            dto.setNomeCampanha("Sem Campanha");
        }

        if(BooleanUtils.isTrue(funil.getAtivo())){
            dto.setAtivo("Ativo");
        }else{
            dto.setAtivo("Desativado");
        }
    }
}
