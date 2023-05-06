package br.com.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OportunidadeTableDTO {

    private String uuid;
    private String nome;
    private String nomeOportunidade;
    private String nomeFunil;
    private String etapaDoFunil;
    private String fonte;
    private String campanha;

    public OportunidadeTableDTO(String uuid,String nome, String nomeOportunidade, String nomeFunil, String etapaDoFunil, String fonte, String campanha) {
        this.uuid = uuid;
        this.nome = nome;
        this.nomeOportunidade = nomeOportunidade;
        this.nomeFunil = nomeFunil;
        this.etapaDoFunil = etapaDoFunil;
        this.fonte = fonte;
        this.campanha = campanha;
    }
}
