package br.com.Service;

import br.com.DTO.ClienteDTO;
import br.com.DTO.ClienteModalDTO;
import br.com.DTO.ContatoDTO;
import br.com.DTO.EnderecoDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Model.*;
import br.com.Repository.CaptacaoRepository;
import br.com.Repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.List;

@ApplicationScoped
public class ClienteService extends Service {

    @Inject
    private ContatoService contatoService;

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private CaptacaoRepository captacaoRepository;

    @Transactional
    public Response create(String json) {
        ClienteDTO clienteDTO = gson.fromJson(json, ClienteDTO.class);
        validaDTO(clienteDTO);
        Cliente cliente = montaCliente(clienteDTO);
        cliente = clienteRepository.saveCliente(cliente);
        return Response.ok(cliente).build();
    }


    private Cliente montaCliente(ClienteDTO clienteDTO, Cliente cliente, Endereco endereco, Contato contato) {
        cliente.setNome(clienteDTO.getNome());
        cliente.setSobrenome(clienteDTO.getSobrenome());
        cliente.setCargo(clienteDTO.getCargo());
        cliente.setSetor(clienteDTO.getSetor());
        if (clienteDTO.getContato() != null) {
            ContatoDTO contatoDTO = clienteDTO.getContato();
            contato.setTelefone(contatoDTO.getTelefone());
            contato.setTelefone2(contatoDTO.getTelefone2());
            contato.setEmail(contatoDTO.getEmail());
            cliente.setContato(contato);
        }
        if (clienteDTO.getEndereco() != null) {
            EnderecoDTO enderecoDTO = clienteDTO.getEndereco();
            endereco.setLogradouro(enderecoDTO.getLogradouro());
            endereco.setCidade(enderecoDTO.getCidade());
            endereco.setEstado(enderecoDTO.getEstado());
            endereco.setPais(enderecoDTO.getPais());
            endereco.setCep(enderecoDTO.getCep());
            cliente.setEndereco(endereco);
        }
        Captacao captacao = null;
        if(clienteDTO.getCaptacao() != null){
            if(clienteDTO.getCaptacao().getFunil() != null && StringUtil.stringValida(clienteDTO.getCaptacao().getFunil().getUuid())){
                 captacao = captacaoRepository.findByFunil(clienteDTO.getCaptacao().getFunil().getUuid());
                 if(captacao == null){
                     captacao = new Captacao();
                     captacao.setFunil(new Funil());
                     captacao.getFunil().setUuid(clienteDTO.getCaptacao().getFunil().getUuid());
                 }
            }else if (clienteDTO.getCaptacao().getCampanha() != null && StringUtil.stringValida(clienteDTO.getCaptacao().getCampanha().getUuid())){
                captacao = captacaoRepository.findByCampanha(clienteDTO.getCaptacao().getCampanha().getUuid());
                if(captacao == null){
                    captacao = new Captacao();
                    captacao.setFunil(new Funil());
                    captacao.getFunil().setUuid(clienteDTO.getCaptacao().getFunil().getUuid());
                }
            }
            if(captacao != null){
                em.persist(captacao);
            }
            cliente.setCaptacao(captacao);
        }
        cliente.setMinFaturamento(clienteDTO.getMinFaturamento());
        cliente.setMaxFaturamento(clienteDTO.getMaxFaturamento());
        return cliente;
    }

    private Cliente montaCliente(ClienteDTO clienteDTO) {
        return montaCliente(clienteDTO, new Cliente(), new Endereco(), new Contato());
    }

    private Cliente montaCliente(ClienteDTO clienteDTO, Cliente cliente) {
        return montaCliente(clienteDTO, cliente, cliente.getEndereco(), cliente.getContato());
    }

    public void validaDTO(ClienteDTO clienteDTO) {
        validaDTO(clienteDTO, new Validacoes());
    }

    public void validaDTO(ClienteDTO clienteDTO, Validacoes validacoes) {
        Boolean validacaoNoExists = validacoes == null;
        if (validacaoNoExists) {
            validacoes = new Validacoes();
        }

        if (!StringUtil.stringValida(clienteDTO.getNome())) {
            validacoes.add("Nome invalido", "Verifique se adicionou um nome valido");
        }

        if (!StringUtil.stringValida(clienteDTO.getSobrenome())) {
            validacoes.add("Sobrenome invalido", "Verifique se adicionou um sobrenome valido");
        }

        if (clienteDTO.getContato() != null) {
            contatoService.validaContato(clienteDTO.getContato(), validacoes);
        } else {
            validacoes.add("Contato Invalido", "Verifique se informou o contato corretamente");
        }

        if (clienteDTO.getCaptacao() == null || !StringUtil.stringValida(clienteDTO.getCaptacao().getUuid())) {
            validacoes.add("Captação invalida", "Favor insira uma captação valida");
        }


        if (validacaoNoExists) {
            validacoes.lancaErro();
        }
    }

    @Transactional
    public Response update(String uuid, String json) {
        ClienteDTO clienteDTO = gson.fromJson(json, ClienteDTO.class);
        validaDTO(clienteDTO);
        Cliente cliente = clienteRepository.findByUuid(uuid);
        if (cliente != null) {
            Cliente clienteUpdated = montaCliente(clienteDTO, cliente);
            clienteRepository.saveCliente(clienteUpdated);
            return Response.ok(clienteUpdated).build();
        } else {
            return Response.status(404).build();
        }
    }

    public Response clientesModal() {
        List<ClienteModalDTO> clienteModalDTOS = this.clienteRepository.findClienteModal();
        return Response.ok(clienteModalDTOS).build();
    }
}
