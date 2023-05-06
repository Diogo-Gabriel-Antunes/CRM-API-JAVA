package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import br.com.Model.Enum.Estado;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Entity
@Setter
public class Endereco extends EntidadeGenerica {


    private String logradouro;
    private String cidade;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String pais;
    private String cep;


}
