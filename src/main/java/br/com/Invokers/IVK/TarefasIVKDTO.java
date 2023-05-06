package br.com.Invokers.IVK;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarefasIVKDTO {

    private String uuid;
    private String tipoDeTarefa;
    private String horaMarcada;
    private String nomeCliente;
    private String telefoneCliente;
    private String emailCliente;

}
