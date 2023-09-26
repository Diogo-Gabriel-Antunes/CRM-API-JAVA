package br.com.DTO;

import br.com.Model.AA.EntidadeGenerica;
import br.com.Model.Captacao;
import br.com.Model.Contato;
import br.com.Model.Empresa;
import br.com.Model.Endereco;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.OneToOne;

@Getter
@Setter
public class ClienteDTO  {
    private String uuid;
    private String nome;
    private String sobrenome;
    private ContatoDTO contato;
    private EnderecoDTO endereco;
    private String cargo;
    private String setor;
    private Double minFaturamento;
    private Double maxFaturamento;
    private EmpresaDTO empresa;
    private CaptacaoDTO captacao;
}
