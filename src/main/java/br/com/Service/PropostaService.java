package br.com.Service;

import br.com.DTO.PropostaDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Model.Cliente;
import br.com.Model.Proposta;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class PropostaService extends Service {

    public Response create(String json) {
        PropostaDTO dto = gson.fromJson(json, PropostaDTO.class);
        validaDTO(dto);
        Proposta proposta = montaProposta(dto);
        em.persist(proposta);
        return Response.ok(proposta).build();
    }

    private Proposta montaProposta(PropostaDTO dto) {
        Proposta proposta = new Proposta();
        proposta.setDataProposta(dto.getDataProposta());
        proposta.setStatusDaProposta(dto.getStatusDaProposta());
        proposta.setOrcamento(dto.getOrcamento());

        if (dto.getCliente() != null) {
            Optional<Cliente> cliente = Cliente.findByIdOptional(dto.getCliente().getUuid());
            cliente.ifPresent(proposta::setCliente);
        }

        return proposta;


    }

    private void validaDTO(PropostaDTO dto) {
        Validacoes validacoes = new Validacoes();

        if (dto.getDataProposta() == null) {
            validacoes.add("Data da proposta invalida", "Digite uma data valida");
        }

        if (dto.getCliente() == null || dto.getCliente().getUuid() == null) {
            validacoes.add("Cliente invalido", "Selecione um cliente valido");
        }

        if (dto.getOrcamento() == null) {
            validacoes.add("Orçamento invalido", "Digite um orçamento valido");
        }

        if (dto.getStatusDaProposta() == null) {
            validacoes.add("Status da proposta invalido", "Selecione um status valido");
        }

        validacoes.lancaErro();
    }
}
