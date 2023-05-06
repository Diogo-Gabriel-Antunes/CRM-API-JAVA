package br.com.DTO;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoDTO {
    private String uuid;
    private String telefone;
    private String telefone2;
    private String email;
}
