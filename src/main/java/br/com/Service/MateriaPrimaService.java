package br.com.Service;

import br.com.DTO.MateriaPrimaDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Model.MateriaPrima;
import br.com.Repository.MateriaPrimaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.List;

@ApplicationScoped
public class MateriaPrimaService extends Service{

    @Inject
    private MateriaPrimaRepository materiaPrimaRepository;

    public Response getAll(Integer offset, Integer limit, String nome) {
        List<MateriaPrima> materiaPrimas = materiaPrimaRepository.findAll(offset,limit,nome);

        return Response.ok(materiaPrimas).build();
    }

    public Response getOne(String uuid) {
        MateriaPrima materiaPrima = materiaPrimaRepository.findByUuid(uuid);

        MateriaPrimaDTO materiaPrimaDTO = montaMateriaPrimaDTO(materiaPrima);
        return Response.ok(materiaPrimaDTO).build();
    }

    private MateriaPrimaDTO montaMateriaPrimaDTO(MateriaPrima materiaPrima) {
        MateriaPrimaDTO materiaPrimaDTO = new MateriaPrimaDTO();

        materiaPrimaDTO.setNome(materiaPrima.getNome());
        materiaPrimaDTO.setQuantidade(materiaPrima.getQuantidadeEstoque());
        return materiaPrimaDTO;
    }

    @Transactional
    public Response create(String json) {
        MateriaPrimaDTO materiaPrimaDTO = gson.fromJson(json, MateriaPrimaDTO.class);
        validaDTO(materiaPrimaDTO);
        MateriaPrima materiaPrima = montaMateriaPrima(materiaPrimaDTO);
        em.persist(materiaPrima);
        return Response.ok(materiaPrima).build();
    }

    private MateriaPrima montaMateriaPrima(MateriaPrimaDTO materiaPrimaDTO) {
        MateriaPrima materiaPrima = new MateriaPrima();

        materiaPrima.setNome(materiaPrimaDTO.getNome());
        materiaPrima.setQuantidadeEstoque(materiaPrimaDTO.getQuantidade());

        return materiaPrima;
    }

    private void montaMateriaPrima(MateriaPrima materiaPrima,MateriaPrimaDTO materiaPrimaDTO) {
        if(StringUtil.stringValida(materiaPrimaDTO.getNome())){
            materiaPrima.setNome(materiaPrimaDTO.getNome());
        }

        if(materiaPrimaDTO.getQuantidade() != null){
            materiaPrima.setQuantidadeEstoque(materiaPrimaDTO.getQuantidade());
        }
    }
    private void validaDTO(MateriaPrimaDTO dto) {
        Validacoes validacoes = new Validacoes();

        if(!StringUtil.stringValida(dto.getNome())){
            validacoes.add("Nome invalido","Insira um nome valido");
        }

        validacoes.lancaErro();
    }

    public Response update(String uuid, String json) {
        MateriaPrima materiaPrima = materiaPrimaRepository.findByUuid(uuid);
        MateriaPrimaDTO materiaPrimaDTO = gson.fromJson(json, MateriaPrimaDTO.class);
        montaMateriaPrima(materiaPrima,materiaPrimaDTO);
        em.persist(materiaPrima);

        return Response.ok(materiaPrima).build();

    }
}
