package br.com.Security.Model;

import br.com.Model.AA.EntidadeGenerica;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Modulo extends EntidadeGenerica {

    private String modulo;
    @OneToMany
    private List<Rota> rotas;
}
