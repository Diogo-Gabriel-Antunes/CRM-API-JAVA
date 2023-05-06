package br.com.DTO;

import br.com.Model.Enum.Estado;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
    private String uuid;
    private String logradouro;
    private String cidade;
    private Estado estado;
    private String pais;
    private String cep;
}
