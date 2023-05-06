package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Tarefa extends EntidadeGenerica {

    @Enumerated(EnumType.STRING)
    private TipoDeTarefa tipoDeTarefa;
    private LocalDateTime horaMarcada;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Empresa empresa;

    @Getter
    public enum TipoDeTarefa{
        LIGACAO("Ligação"),EMAIL("E-mail"),VISITA("Visita"),REUNIAO("Reunião"),TAREFA("Tarefa"),ALMOCO("Almoço"),WHATSAPP("Whatsapp");

        private String tipo;

        TipoDeTarefa(String tipo) {
            this.tipo = tipo;
        }
    }
}
