package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AnotacoesLead extends EntidadeGenerica {

    private String anotacao;
    private Boolean checkbox;
    private String topico;
    private Boolean checkboxConcluido;
}
