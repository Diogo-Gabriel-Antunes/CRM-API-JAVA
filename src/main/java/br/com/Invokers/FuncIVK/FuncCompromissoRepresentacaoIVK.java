package br.com.Invokers.FuncIVK;


import br.com.DTO.OportunidadeDTO;
import br.com.DTO.TarefaDTO;
import br.com.Invokers.IVK.CompromissoRepresentacaoIVK;
import br.com.Model.Compromisso;
import org.acme.Util.InterfacesUtil.Invoker;

public class FuncCompromissoRepresentacaoIVK implements Invoker {


    private Compromisso model;
    private CompromissoRepresentacaoIVK dto;

    public FuncCompromissoRepresentacaoIVK(Compromisso model, CompromissoRepresentacaoIVK dto) {
        this.model = model;
        this.dto = dto;
    }


    public void invoke(){
        dto.setInicioCompromisso(model.getInicioCompromisso());
        dto.setFimCompromisso(model.getFimCompromisso());
        dto.setDiaDaSemana(model.getDiaDaSemana());
        dto.setHorario(model.getHorario());
        dto.setTipoCompromisso(model.getTipoCompromisso());
        dto.setDiaDoMes(model.getDiaDoMes());
        if(model.getOportunidades() != null){
            dto.setOportunidadeDTO(new OportunidadeDTO());
            dto.getOportunidadeDTO().setUuid(model.getOportunidades().getUuid());
        }

        if(model.getTarefas() != null){
            dto.setTarefaDTO(new TarefaDTO());
            dto.getTarefaDTO().setUuid(model.getTarefas().getUuid());
        }

    }
}
