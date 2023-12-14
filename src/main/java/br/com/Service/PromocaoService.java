package br.com.Service;

import br.com.DTO.ItensPromocaoDTO;
import br.com.DTO.PromocaoDTO;
import br.com.Invokers.FuncIVK.FuncPromocaoRepresentacaoIVK;
import br.com.Invokers.IVK.PromocaoRepresentacaoIVK;
import br.com.Model.ItensPromocao;
import br.com.Model.Produto;
import br.com.Model.Promocao;
import br.com.Repository.ProdutoRepository;
import br.com.Repository.PromocaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PromocaoService extends Service {

    @Inject
    private PromocaoRepository promocaoRepository;

    @Inject
    private ProdutoRepository produtoRepository;

    public Response getAll(Integer offset, Integer limit) {
        List<Promocao> promocaos = promocaoRepository.findAll(offset,limit);

        if(ArrayUtil.validaArray(promocaos)){

            List<PromocaoRepresentacaoIVK> list = new ArrayList<>();
            for (Promocao promocao : promocaos) {
                PromocaoRepresentacaoIVK ivk = new PromocaoRepresentacaoIVK();
                fieldUtil.invokerExecutor(new FuncPromocaoRepresentacaoIVK(promocao,ivk));
                list.add(ivk);
            }
            return Response.ok(list).build();
        }else{
            return Response.ok(new ArrayList<>()).build();
        }
    }

    public Response getOne(String uuid) {
        Promocao promocao = promocaoRepository.findByUuid(uuid);
        return Response.ok(promocao).build();
    }

    public Response create(String json) {
        PromocaoDTO promocaoDTO = gson.fromJson(json, PromocaoDTO.class);
        Promocao promocao = new Promocao();
        montaPromocao(promocao,promocaoDTO);
        em.persist(promocao);
        PromocaoRepresentacaoIVK ivk = new PromocaoRepresentacaoIVK();
        fieldUtil.invokerExecutor(new FuncPromocaoRepresentacaoIVK(promocao,ivk));
        return Response.ok(ivk).build();
    }



    public Response update(String uuid, String json) {
        Promocao promocao = promocaoRepository.findByUuid(uuid);
        PromocaoDTO promocaoDTO = gson.fromJson(json, PromocaoDTO.class);
        montaPromocao(promocao,promocaoDTO);
        em.persist(promocao);
        PromocaoRepresentacaoIVK ivk = new PromocaoRepresentacaoIVK();
        fieldUtil.invokerExecutor(new FuncPromocaoRepresentacaoIVK(promocao,ivk));
        return Response.ok(ivk).build();
    }

    private void montaPromocao(Promocao promocao, PromocaoDTO promocaoDTO) {
        promocao.setNomePromocao(promocaoDTO.getNomePromocao());
        promocao.setPorcetagem(promocaoDTO.getPorcetagem());
        promocao.setItensPromocaos(new ArrayList<>());
        for (ItensPromocaoDTO itensPromocao : promocaoDTO.getItensPromocaos()) {
            ItensPromocao item = new ItensPromocao();
            Produto produto = produtoRepository.findByUuid(itensPromocao.getProdutos().getUuid());
            item.setProdutos(produto);
            item.setQuantidade(itensPromocao.getQuantidade());
            em.persist(item);
            promocao.getItensPromocaos().add(item);
        }

    }
}
