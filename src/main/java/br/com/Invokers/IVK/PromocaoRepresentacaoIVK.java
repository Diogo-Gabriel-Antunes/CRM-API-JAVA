package br.com.Invokers.IVK;

import lombok.Data;

@Data
public class PromocaoRepresentacaoIVK {
    private String uuid;
    private String nomePromocao;
    private Double porcetagem;

    private Long quantidadeDeProdutos;
}
