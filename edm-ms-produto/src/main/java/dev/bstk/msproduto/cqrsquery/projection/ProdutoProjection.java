package dev.bstk.msproduto.cqrsquery.projection;

import dev.bstk.msproduto.cqrscommand.data.Produto;
import dev.bstk.msproduto.cqrscommand.data.ProdutoRepository;
import dev.bstk.msproduto.cqrsquery.api.response.ProdutoResponse;
import dev.bstk.msproduto.cqrsquery.queries.ProdutosPorQuantidadeQuery;
import dev.bstk.msproduto.cqrsquery.queries.ProdutosPorValorQuery;
import dev.bstk.msproduto.cqrsquery.queries.ProdutosQuery;
import dev.bstk.msproduto.util.CollectionHelper;
import dev.bstk.msproduto.util.Mapper;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ProdutoProjection {

    private final ProdutoRepository repository;


    @QueryHandler
    public List<ProdutoResponse> handler(final ProdutosQuery query) {
        final List<Produto> produtos = repository.findAll();
        return Mapper.list(produtos, ProdutoResponse.class);
    }

    @QueryHandler
    public List<ProdutoResponse> handler(final ProdutosPorValorQuery query) {
        if (Objects.isNull(query)) {
            throw new IllegalArgumentException("Query não pode ser nula!");
        }

        final List<Produto> produtos = repository.produtosValorDeAte(query.getDe(), query.getAte());

        return CollectionHelper.isEmpty(produtos)
            ? Collections.emptyList()
            : Mapper.list(produtos, ProdutoResponse.class);
    }

    @QueryHandler
    public List<ProdutoResponse> handler(final ProdutosPorQuantidadeQuery query) {
        if (Objects.isNull(query)) {
            throw new IllegalArgumentException("Query não pode ser nula!");
        }

        final List<Produto> produtos = repository.produtosQuantidadeDeAte(query.getDe(), query.getAte());

        return CollectionHelper.isEmpty(produtos)
            ? Collections.emptyList()
            : Mapper.list(produtos, ProdutoResponse.class);
    }
}
