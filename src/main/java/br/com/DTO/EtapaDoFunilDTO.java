package br.com.DTO;

import br.com.Model.Funil;
import br.com.Model.HistoricoDeEtapasFunil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EtapaDoFunilDTO {
    private String uuid;
    private String etapa;
    private Boolean ativo;
    private FunilDTO funil;
}
