package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Captacao extends EntidadeGenerica {

    @ManyToOne
    private Funil funil;
}
