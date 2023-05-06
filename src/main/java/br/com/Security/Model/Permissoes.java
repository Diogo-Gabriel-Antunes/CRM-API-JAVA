package br.com.Security.Model;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Permissoes extends EntidadeGenerica {

    private Boolean leitura;
    private Boolean insercao;
    private Boolean edicao;
    private Boolean exclusao;
    @ManyToOne
    private Rota rota;
    @ManyToMany(mappedBy = "permissoes")
    private List<Usuario> usuarios;
}
