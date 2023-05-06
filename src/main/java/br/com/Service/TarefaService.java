package br.com.Service;

import br.com.DTO.TarefaDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Invokers.FuncIVK.TarefasTableIVK;
import br.com.Invokers.IVK.TarefasIVKDTO;
import br.com.Model.Cliente;
import br.com.Model.Empresa;
import br.com.Model.Tarefa;
import br.com.Repository.TarefaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TarefaService extends Service {


    @Inject
    TarefaRepository tarefaRepository;

    @Transactional
    public Response create(String json) {
        TarefaDTO dto = gson.fromJson(json, TarefaDTO.class);
        validaDTO(dto);
        Tarefa tarefa = montaTarefa(dto);
        em.persist(tarefa);
        return Response.ok(tarefa).build();
    }

    @Transactional
    public Tarefa montaTarefa(TarefaDTO dto) {
        Tarefa tarefa = new Tarefa();

        tarefa.setTipoDeTarefa(dto.getTipoDeTarefa());
        tarefa.setHoraMarcada(dto.getHoraMarcada());
        if (dto.getCliente() != null) {
            Optional<Cliente> cliente = Cliente.findByIdOptional(dto.getCliente().getUuid());
            cliente.ifPresent(tarefa::setCliente);
        }
        if (dto.getEmpresa() != null) {
            Optional<Empresa> empresa = Empresa.findByIdOptional(dto.getEmpresa().getUuid());
            empresa.ifPresent(tarefa::setEmpresa);
        }
        return tarefa;
    }

    public void validaDTO(Validacoes validacoes,TarefaDTO dto) {
        Boolean validacoesNotExists = validacoes == null;
        if(validacoesNotExists){
            validacoes = new Validacoes();
        }

        if (dto.getTipoDeTarefa() == null) {
            validacoes.add("Tipo de Tarefa Invalido", "Favor informar um tipo tarefa valido");
        }

        if (dto.getHoraMarcada() == null) {
            validacoes.add("Hora invalida", "Favor informar uma hora valida");
        }

        if (dto.getCliente() == null || dto.getCliente().getUuid() == null) {
            validacoes.add("Cliente invalido", "Tente informar um cliente valido");
        }

        if(validacoesNotExists){
            validacoes.lancaErro();
        }
    }

    public void validaDTO(TarefaDTO dto) {
       validaDTO(new Validacoes(),dto);
    }

    public Response listAll() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        if(tarefas == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }else{
            List<TarefasIVKDTO> ivkdtos = new ArrayList<>();
            for (Tarefa tarefa : tarefas) {
                TarefasIVKDTO ivk = new TarefasIVKDTO();
                fieldUtil.invokerExecutor(new TarefasTableIVK(tarefa,ivk));
                ivkdtos.add(ivk);
            }
            return Response.ok(ivkdtos).build();
        }
    }
}
