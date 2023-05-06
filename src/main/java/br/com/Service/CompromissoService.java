package br.com.Service;

import br.com.DTO.CompromissoDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Model.Compromisso;
import br.com.Model.Oportunidade;
import br.com.Model.Tarefa;

import br.com.Repository.OportunidadeRepository;
import br.com.Repository.TarefaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class CompromissoService extends Service {

    @Inject
    OportunidadeRepository oportunidadeRepository;

    @Inject
    TarefaRepository tarefaRepository;

    public void validaDTO(CompromissoDTO dto){
        validaDTO(new Validacoes(),dto);
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
        if(oportunidade != null){
            compromisso.setOportunidades(oportunidade);
        }else if(tarefa != null){
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
        if(dto.getOportunidades() != null && dto.getOportunidades().getUuid() != null){
            Oportunidade oportunidade = oportunidadeRepository.findByUuid(dto.getOportunidades().getUuid());
            compromisso.setOportunidades(oportunidade);
        }else if (dto.getTarefas() != null && dto.getTarefas().getUuid() != null){
            Tarefa tarefa = tarefaRepository.findByUuid(dto.getTarefas().getUuid());
            compromisso.setTarefas(tarefa);
        }
        return compromisso;
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
        Compromisso compromisso =  createCompromisso(dto);
        em.persist(compromisso);
        return Response.ok(compromisso).build();

    }
}
