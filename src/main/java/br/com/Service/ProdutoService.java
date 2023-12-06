package br.com.Service;


import br.com.DTO.ProdutoDTO;
import br.com.Infra.Exceptions.Validacoes;
import br.com.Invokers.FuncIVK.FuncItensMateriaPrimaRepresentacaoIVK;
import br.com.Invokers.FuncIVK.FuncProdutoRepresentacaoIVK;
import br.com.Invokers.IVK.ItensMateriaPrimaRepresentacaoIVKDTO;
import br.com.Invokers.IVK.ProdutoRepresentacaoIVK;
import br.com.Model.ItensMateriaPrima;
import br.com.Model.MateriaPrima;
import br.com.Model.Produto;
import br.com.Repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.ArrayUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ApplicationScoped
public class ProdutoService extends Service {

    @Inject
    private ProdutoRepository produtoRepository;

    public Response listAll(Integer offset) {
        List<Produto> produtos = produtoRepository.listAll(offset);
        if (ArrayUtil.validaArray(produtos)) {
            return Response.ok(produtos).build();
        } else {
            return Response.noContent().build();
        }

    }

    public Response getOne(String uuid) {
        if (StringUtil.stringValida(uuid)) {
            Produto produto = produtoRepository.findByUuid(uuid);
            return Response.ok(produto).build();
        } else {
            return Response.status(404).build();
        }
    }

    @Transactional
    public Response create(String json) {
        ProdutoDTO dto = gson.fromJson(json, ProdutoDTO.class);
        validaDTO(dto);
        Produto produto = montaProduto(dto);
        em.persist(produto);
        return Response.ok(produto).build();
    }

    private Produto montaProduto(ProdutoDTO dto) {
        Produto produto = new Produto();
        setFields(dto, produto);
        return produto;
    }

    private static void setFields(ProdutoDTO dto, Produto produto) {
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
    }

    private void validaDTO(ProdutoDTO dto) {
        Validacoes validacoes = new Validacoes();

        if (!StringUtil.stringValida(dto.getNome())) {
            validacoes.add("Nome produto Invalido", "Arrumar nome produto");
        }

        validacoes.lancaErro();
    }

    @Transactional
    public Response update(String uuid, String json) {
        Produto produto = produtoRepository.findByUuid(uuid);
        ProdutoDTO dto = gson.fromJson(json, ProdutoDTO.class);
        validaDTO(dto);
        setFields(dto, produto);
        em.persist(produto);

        ProdutoRepresentacaoIVK ivk = new ProdutoRepresentacaoIVK();
        fieldUtil.invokerExecutor(new FuncProdutoRepresentacaoIVK(produto, ivk));
        return Response.ok(ivk).build();
    }

    @Transactional
    public Response delete(String uuid) {
        Produto produto = produtoRepository.findByUuid(uuid);
        if (produto != null) {
            em.remove(produto);
            return Response.ok().build();
        } else {
            return Response.status(404).build();
        }
    }

    public Response getMateriaPrima(String uuid) {
        List<ItensMateriaPrima> materiaPrimas = produtoRepository.getMateriaPrimaByUuid(uuid);


        List<ItensMateriaPrimaRepresentacaoIVKDTO> dtos = new ArrayList<>();

        for (ItensMateriaPrima materiaPrima : materiaPrimas) {
            ItensMateriaPrimaRepresentacaoIVKDTO dto = new ItensMateriaPrimaRepresentacaoIVKDTO();
            fieldUtil.invokerExecutor(new FuncItensMateriaPrimaRepresentacaoIVK(materiaPrima,dto));
            dtos.add(dto);
        }
        return Response.ok(dtos).build();

    }

    public Response createItens(String uuid, String json) {
        Produto produto = produtoRepository.findByUuid(uuid);
        ItensMateriaPrima itensMateriaPrima = gson.fromJson(json, ItensMateriaPrima.class);
        em.persist(itensMateriaPrima);
        if (produto != null && produto.getItensMateriaPrimas() != null) {
            setItens(produto, itensMateriaPrima);
        } else if (produto != null && produto.getItensMateriaPrimas() == null) {
            produto.setItensMateriaPrimas(new HashSet<>());
            setItens(produto, itensMateriaPrima);
        }else if (produto == null) {
            return Response.noContent().build();
        }
        return Response.ok(produto).build();
    }

    private void setItens(Produto produto, ItensMateriaPrima itensMateriaPrima) {
        produto.getItensMateriaPrimas().add(itensMateriaPrima);
    }

}
