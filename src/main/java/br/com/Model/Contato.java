package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Entity
@Setter
public class Contato extends EntidadeGenerica {


    private String telefone;
    private String telefone2;
    private String email;


}
