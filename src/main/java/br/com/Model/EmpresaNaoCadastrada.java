package br.com.Model;

import br.com.Model.AA.EntidadeGenerica;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Getter
@Setter
@Entity
public class EmpresaNaoCadastrada extends EntidadeGenerica {

    private String nomeEmpresa;
    private String segmento;
    private String url;
    @Column(columnDefinition = "TEXT")
    private String resumo;
}
