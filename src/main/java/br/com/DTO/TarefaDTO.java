package br.com.DTO;

import br.com.Model.Cliente;
import br.com.Model.Empresa;
import br.com.Model.Tarefa;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
public class TarefaDTO {

    private String uuid;
    private Tarefa.TipoDeTarefa tipoDeTarefa;
    private LocalDateTime horaMarcada;
    private ClienteDTO cliente;
    private EmpresaDTO empresa;
}
