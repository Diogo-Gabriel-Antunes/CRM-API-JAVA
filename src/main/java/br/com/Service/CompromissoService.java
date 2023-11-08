package br.com.Service;

import br.com.DTO.CompromissoDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Model.*;

import br.com.Repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.HashMap;

@ApplicationScoped
public class CompromissoService extends Service {

    @Inject
    OportunidadeRepository oportunidadeRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    CampanhaRepository campanhaRepository;
    @Inject
    FonteRepository fonteRepository;
    @Inject
    FunilRepository funilRepository;
    @Inject
    TarefaRepository tarefaRepository;

    public void validaDTO(CompromissoDTO dto) {
        validaDTO(new Validacoes(), dto);
    }

    public void validaDTO(Validacoes validacoes, CompromissoDTO dto) {
        Boolean validacoesNotExist = validacoes == null;
        if (validacoesNotExist) {
            validacoes = new Validacoes();
        }

        if (dto.getFimCompromisso() == null) {
            validacoes.add("Data fim invalida", "Insira uma data fim valida");
        }
        if (dto.getInicioCompromisso() == null) {
            validacoes.add("Data Inicio invalido", "Insira uma data inicio valido");
        }
        if (dto.getMes() == null) {
            validacoes.add("Mes invalido", "Insira um mes valido");
        }
        if (dto.getHorario() == null) {
            validacoes.add("Horario invalido", "Insira um Horario valido");
        }
        if (dto.getDiaDoMes() == null) {
            validacoes.add("Mes invalid", "Insira um mes valido");
        }


        if (validacoesNotExist) {
            validacoes.lancaErro();
        }
    }


    public Compromisso createCompromisso(CompromissoDTO dto, Oportunidade oportunidade, Tarefa tarefa) {
        Compromisso compromisso = new Compromisso();
        compromisso.setInicioCompromisso(dto.getInicioCompromisso());
        compromisso.setFimCompromisso(dto.getFimCompromisso());
        compromisso.setDiaDaSemana(dto.getDiaDaSemana());
        compromisso.setMes(dto.getMes());
        compromisso.setHorario(dto.getHorario());
        compromisso.setTipoCompromisso(dto.getTipoCompromisso());
        compromisso.setDiaDoMes(dto.getDiaDoMes());
        if (oportunidade != null) {
            compromisso.setOportunidades(oportunidade);
        } else if (tarefa != null) {
            compromisso.setTarefas(tarefa);
        }
        return compromisso;
    }

    public Compromisso createCompromisso(CompromissoDTO dto) {
        Compromisso compromisso = new Compromisso();
        compromisso.setInicioCompromisso(dto.getInicioCompromisso());
        compromisso.setFimCompromisso(dto.getFimCompromisso());
        compromisso.setDiaDaSemana(dto.getDiaDaSemana());
        compromisso.setMes(dto.getMes());
        compromisso.setHorario(dto.getHorario());
        compromisso.setTipoCompromisso(dto.getTipoCompromisso());
        compromisso.setDiaDoMes(dto.getDiaDoMes());
        if (dto.getOportunidades() != null && dto.getOportunidades().getUuid() != null) {
            Oportunidade oportunidade = oportunidadeRepository.findByUuid(dto.getOportunidades().getUuid());
            if (oportunidade == null) {
                oportunidade = createOportunidade(dto);
                em.persist(oportunidade);
            }
            compromisso.setOportunidades(oportunidade);
        } else if (dto.getTarefas() != null && dto.getTarefas().getUuid() != null) {
            Tarefa tarefa = tarefaRepository.findByUuid(dto.getTarefas().getUuid());
            if (tarefa == null) {
                tarefa = createTarefa(dto);
                em.persist(tarefa);
            }
            compromisso.setTarefas(tarefa);
        }


        return compromisso;
    }

    private Tarefa createTarefa(CompromissoDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setHoraMarcada(dto.getTarefas().getHoraMarcada());
        tarefa.setTipoDeTarefa(dto.getTarefas().getTipoDeTarefa());
        if (dto.getTarefas() != null && dto.getTarefas().getCliente() != null && StringUtil.stringValida(dto.getTarefas().getCliente().getUuid())) {
            Cliente cliente = clienteRepository.findByUuid(dto.getTarefas().getCliente().getUuid());
            tarefa.setCliente(cliente);
        }
        return tarefa;
    }

    private Oportunidade createOportunidade(CompromissoDTO dto) {
        Oportunidade oportunidade = new Oportunidade();
        oportunidade.setNomeOportunidade(dto.getOportunidades().getNomeOportunidade());
        if (dto.getOportunidades() != null && dto.getOportunidades().getCliente() != null && StringUtil.stringValida(dto.getOportunidades().getCliente().getUuid())) {
            Cliente cliente = clienteRepository.findByUuid(dto.getOportunidades().getCliente().getUuid());
            oportunidade.setCliente(cliente);
        }

        if (dto.getOportunidades() != null && dto.getOportunidades().getFunil() != null && StringUtil.stringValida(dto.getOportunidades().getFunil().getUuid())) {
            Funil funil = funilRepository.findByUuid(dto.getOportunidades().getFunil().getUuid());
            oportunidade.setFunil(funil);
        }
        if (dto.getOportunidades() != null && dto.getOportunidades().getCampanha() != null && StringUtil.stringValida(dto.getOportunidades().getCampanha().getUuid())) {
            Campanha campanha = campanhaRepository.findByUuid(dto.getOportunidades().getCampanha().getUuid());
            oportunidade.setCampanha(campanha);
        }
        if (dto.getOportunidades() != null && dto.getOportunidades().getFonte() != null && StringUtil.stringValida(dto.getOportunidades().getFonte().getUuid())) {
            Fonte fonte = fonteRepository.findByUuid(dto.getOportunidades().getFonte().getUuid());
            oportunidade.setFonte(fonte);
        }

        return oportunidade;
    }

    public Response createCompromissoByOportunidade(String json) {
        CompromissoDTO dto = gson.fromJson(json, CompromissoDTO.class);
        validaDTO(dto);
        Compromisso compromisso = createCompromisso(dto);
        em.persist(compromisso);
        return Response.ok(compromisso).build();
    }

    public Response createCompromissoByTarefa(String json) {
        CompromissoDTO dto = gson.fromJson(json, CompromissoDTO.class);
        validaDTO(dto);
        Compromisso compromisso = createCompromisso(dto);
        em.persist(compromisso);
        return Response.ok(compromisso).build();

    }


}
