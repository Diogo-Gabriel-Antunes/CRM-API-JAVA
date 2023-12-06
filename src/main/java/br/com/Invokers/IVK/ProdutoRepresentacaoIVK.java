package br.com.Invokers.IVK;

import br.com.Model.ItensMateriaPrima;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProdutoRepresentacaoIVK {

    private String nome;
    private Double preco;
}
