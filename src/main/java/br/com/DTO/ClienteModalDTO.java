package br.com.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModalDTO {

    private String uuid;
    private String nome;
    private String sobrenome;
    private String cargo;
    private String setor;
    private Double minFaturamento;
    private Double maxFaturamento;
    private String telefone;
    private String telefone2;
    private String email;

    public ClienteModalDTO(String uuid, String nome, String sobrenome, String cargo,
                           String setor, Double minFaturamento, Double maxFaturamento,
                           String telefone, String telefone2, String email) {
        this.uuid = uuid;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.setor = setor;
        this.minFaturamento = minFaturamento;
        this.maxFaturamento = maxFaturamento;
        this.telefone = telefone;
        this.telefone2 = telefone2;
        this.email = email;
    }
}
