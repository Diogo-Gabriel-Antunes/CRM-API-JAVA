package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

@Getter
@Setter
@Entity
public class Integracoes extends EntidadeGenerica {

    private Boolean whatsapp;
    private Boolean email;
    private Boolean googleMeet;
}
