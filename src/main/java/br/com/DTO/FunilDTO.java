package br.com.DTO;

import lombok.Data;

import java.util.List;

@Data
public class FunilDTO {
    private String uuid;

    private String nomeFunil;

    private List<String> integracoes;
}
