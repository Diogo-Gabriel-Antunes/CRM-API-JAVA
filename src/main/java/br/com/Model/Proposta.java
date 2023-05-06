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
public class Proposta extends EntidadeGenerica {

    @Enumerated(EnumType.STRING)
    private StatusDaProposta statusDaProposta;
    private LocalDateTime dataProposta;
    private Double orcamento;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Empresa empresa;

    public enum StatusDaProposta{
        CANCELADA("Cancelada"),
        EM_ANDAMENTO("Em andamento"),
        SEM_RESPOSTA("Sem resposta"),
        CONCLUIDA("Concluida");

        private String status;

        StatusDaProposta(String status) {
            this.status = status;
        }
    }
}
