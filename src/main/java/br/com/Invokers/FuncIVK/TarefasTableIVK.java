package br.com.Invokers.FuncIVK;


import br.com.Invokers.IVK.TarefasIVKDTO;
import br.com.Model.Tarefa;
import org.acme.Util.InterfacesUtil.Invoker;
import org.acme.Util.LocalDateTimeUtil;

public class TarefasTableIVK implements Invoker {

    Tarefa tarefa;
    TarefasIVKDTO ivk;

    public TarefasTableIVK(Tarefa tarefa, TarefasIVKDTO ivk) {
        this.tarefa = tarefa;
        this.ivk = ivk;
    }

    public void invoker() {
        ivk.setUuid(tarefa.getUuid());
        if (tarefa.getTipoDeTarefa() != null) {
            ivk.setTipoDeTarefa(tarefa.getTipoDeTarefa().getTipo());
        }
        if (tarefa.getHoraMarcada() != null) {
            ivk.setHoraMarcada(LocalDateTimeUtil.retornaYYYYMMDDTHHMMSS(tarefa.getHoraMarcada()));
        }
        if (tarefa.getCliente() != null) {
            if (tarefa.getCliente().getNome() != null) {
                ivk.setNomeCliente(tarefa.getCliente().getNome());
            }
            if (tarefa.getCliente().getContato() != null && tarefa.getCliente().getContato().getTelefone() != null) {
                ivk.setTelefoneCliente(tarefa.getCliente().getContato().getTelefone());
                if (tarefa.getCliente().getContato().getTelefone2() != null) {
                    ivk.setTelefoneCliente(ivk.getTelefoneCliente() + " - " + tarefa.getCliente().getContato().getTelefone2());
                }
            }
            if (tarefa.getCliente().getContato().getEmail() != null) {
                ivk.setEmailCliente(tarefa.getCliente().getContato().getEmail());
            }
        }
    }
}
