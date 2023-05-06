package br.com.DTO;

import br.com.Model.*;
import lombok.Data;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

@Data
public class OportunidadeDTO {
    private String uuid;
    private String nomeOportunidade;

    private FunilDTO funil;
    private EtapaDoFunilDTO etapaDoFunil;

    private FonteDTO fonte;

    private CampanhaDTO campanha;

    private EmpresaDTO empresa;

    private ClienteDTO cliente;
    private CompromissoDTO compromisso;

}
