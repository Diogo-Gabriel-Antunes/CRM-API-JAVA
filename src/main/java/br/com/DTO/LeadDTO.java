package br.com.DTO;

import br.com.Model.Campanha;
import br.com.Model.Cliente;
import br.com.Model.EtapaDoFunil;
import br.com.Model.Funil;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LeadDTO {
    private String uuid;
    private String descricao;
    private LocalDateTime data;
    private String funilUuid;
    private String etapaDoFunilUuid;
    private String campanhaUuid;
    private Double valor;
    private String clienteUuid;
}
