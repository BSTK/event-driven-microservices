package dev.bstk.msproduto.cqrsquery.api;

import dev.bstk.msproduto.cqrsquery.api.response.ProdutoResponse;
import dev.bstk.msproduto.cqrsquery.domain.queries.ProdutosPorQuantidadeQuery;
import dev.bstk.msproduto.cqrsquery.domain.queries.ProdutosPorValorQuery;
import dev.bstk.msproduto.cqrsquery.domain.queries.ProdutosQuery;
import dev.bstk.msproduto.data.Produto;
import dev.bstk.msproduto.util.CollectionHelper;
import dev.bstk.msproduto.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/produtos")
public class ProdutoQueryResource {

    private final QueryGateway queryGateway;


    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> produtos() {
        final List<Produto> produtos = queryGateway
            .query(
                ProdutosQuery.builder().build(),
                ResponseTypes.multipleInstancesOf(Produto.class))
            .join();

        return response(produtos);
    }

    @GetMapping("/valor")
    public ResponseEntity<List<ProdutoResponse>> produtosPorValor(
        @RequestParam(value = "de", required = false) final BigDecimal valorDe,
        @RequestParam(value = "ate", required = false) final BigDecimal valorAte) {

        final List<Produto> produtos = queryGateway
            .query(
                ProdutosPorValorQuery
                    .builder()
                    .de(valorDe)
                    .ate(valorAte)
                    .build(),
                ResponseTypes.multipleInstancesOf(Produto.class))
            .join();

        return response(produtos);
    }

    @GetMapping("/quantidade")
    public ResponseEntity<List<ProdutoResponse>> produtosPorQuantidade(
        @RequestParam(value = "de", required = false) final Integer quantidadeDe,
        @RequestParam(value = "ate", required = false) final Integer quantidadeAte) {

        final List<Produto> produtos = queryGateway
            .query(
                ProdutosPorQuantidadeQuery
                    .builder()
                    .de(quantidadeDe)
                    .ate(quantidadeAte)
                    .build(),
                ResponseTypes.multipleInstancesOf(Produto.class))
            .join();

        return response(produtos);
    }

    private ResponseEntity<List<ProdutoResponse>> response(final List<Produto> response) {
        return CollectionHelper.isEmpty(response)
            ? ResponseEntity
                .noContent()
                .build()
            : ResponseEntity
                .ok()
                .body(Mapper.list(response, ProdutoResponse.class));
    }
}
