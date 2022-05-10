package dev.bstk.msproduto.cqrsquery.domain.projection;

import dev.bstk.msproduto.cqrsquery.domain.queries.ProdutosPorQuantidadeQuery;
import dev.bstk.msproduto.cqrsquery.domain.queries.ProdutosPorValorQuery;
import dev.bstk.msproduto.cqrsquery.domain.queries.ProdutosQuery;
import dev.bstk.msproduto.data.Produto;
import dev.bstk.msproduto.data.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ProdutoProjection {

    private final ProdutoRepository repository;


    @QueryHandler
    public List<Produto> handler(final ProdutosQuery query) {
        return repository.findAll();
    }

    @QueryHandler
    public List<Produto> handler(final ProdutosPorValorQuery query) {
        validarQuery(query);
        return repository.produtosValorDeAte(query.getDe(), query.getAte());
    }

    @QueryHandler
    public List<Produto> handler(final ProdutosPorQuantidadeQuery query) {
        validarQuery(query);
        return repository.produtosQuantidadeDeAte(query.getDe(), query.getAte());
    }

    private void validarQuery(final Object query) {
        if (Objects.isNull(query)) {
            throw new IllegalArgumentException("Query não pode ser nula!");
        }
    }
}
