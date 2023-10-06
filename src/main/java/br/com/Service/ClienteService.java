package br.com.Service;

import br.com.DTO.*;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Model.*;
import br.com.Repository.CaptacaoRepository;
import br.com.Repository.ClienteRepository;
import br.com.Repository.EtapaDoFunilRepository;
import br.com.Repository.FunilRepository;
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

    @Inject
    private FunilRepository funilRepository;


    @Inject
    private EtapaDoFunilRepository etapaDoFunilRepository;

    @Transactional
    public Response create(String json) {
        ClienteAgendamentoDTO clienteAgendamentoDTO = gson.fromJson(json, ClienteAgendamentoDTO.class);
        validaDTO(clienteAgendamentoDTO.getCliente());
        Cliente cliente = montaCliente(clienteAgendamentoDTO.getCliente());
        cliente = clienteRepository.saveCliente(cliente);
        Compromisso compromisso = montaCompromisso(clienteAgendamentoDTO.getAgendamento(),cliente);
        return Response.ok(cliente).build();
    }

    private Compromisso montaCompromisso(ClienteAgendamentoDTO.AgendamentoDTO agendamento, Cliente cliente) {
        Compromisso compromisso = new Compromisso();
        compromisso.setInicioCompromisso(agendamento.getDataAgendamento());
        compromisso.setFimCompromisso(agendamento.getDataAgendamento().plusHours(1));

        if(ClienteAgendamentoDTO.TipoAgendamento.TAREFA.equals(agendamento.getTipoAgendamento())){
            compromisso.setTipoCompromisso(Compromisso.TipoCompromisso.TAREFA);
            Tarefa tarefa = new Tarefa();
            if(cliente !=null){
                tarefa.setCliente(cliente);
            }
            tarefa.setTipoDeTarefa(agendamento.getTipoDaTarefa());
            tarefa.setHoraMarcada(agendamento.getDataAgendamento());
            em.persist(tarefa);
            compromisso.setTarefas(tarefa);
        }else{
            compromisso.setTipoCompromisso(Compromisso.TipoCompromisso.OPORTUNIDADE);
            Oportunidade oportunidade = new Oportunidade();
            if(cliente != null){
                oportunidade.setCliente(cliente);
            }
            if(StringUtil.stringValida(agendamento.getFunilUuid())){
                Funil funil = funilRepository.findByUuid(agendamento.getFunilUuid());
                oportunidade.setFunil(funil);
            }
            if(StringUtil.stringValida(agendamento.getEtapaDoFunilUuid())){
                EtapaDoFunil etapaDoFunil = etapaDoFunilRepository.findByUuid(agendamento.getEtapaDoFunilUuid());
                oportunidade.setEtapaDoFunil(etapaDoFunil);
            }
            em.persist(oportunidade);
            compromisso.setOportunidades(oportunidade);
        }

        em.persist(compromisso);

        return compromisso;
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

    @Transactional
    public Response delete(String uuid) {
        if(StringUtil.stringValida(uuid)){
            clienteRepository.findByUuid(uuid).delete();
            return Response.ok().build();
        }else{
            return Response.status(404).build();
        }
    }
}
