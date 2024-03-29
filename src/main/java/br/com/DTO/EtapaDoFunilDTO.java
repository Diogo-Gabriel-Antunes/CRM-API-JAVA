package br.com.DTO;

import br.com.Model.Funil;
import br.com.Model.HistoricoDeEtapasFunil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.acme.Util.InterfacesUtil.DTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class EtapaDoFunilDTO implements DTO {
    private String uuid;
    private String etapa;
    private String nivel;
    private Boolean ativo;

    private FunilDTO funil;
    private Boolean finalizacao;
}
