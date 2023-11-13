package br.com.Invokers.IVK;

import br.com.DTO.CampanhaDTO;
import br.com.DTO.EtapaDoFunilDTO;
import lombok.Data;

import java.util.List;

@Data
public class FunilRepresentacaoIVK {

    private String nomeFunil;
    private String listaIntegracoes;
    private CampanhaDTO campanha;
    private Boolean ativo;
    private Boolean padrao;
    private List<EtapaDoFunilDTO> etapaDoFunil;
}
