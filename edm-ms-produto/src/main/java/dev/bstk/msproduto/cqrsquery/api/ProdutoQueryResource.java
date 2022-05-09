package dev.bstk.msproduto.cqrsquery.api;

import dev.bstk.msproduto.cqrsquery.api.response.ProdutoResponse;
import dev.bstk.msproduto.cqrsquery.queries.ProdutosPorQuantidadeQuery;
import dev.bstk.msproduto.cqrsquery.queries.ProdutosPorValorQuery;
import dev.bstk.msproduto.cqrsquery.queries.ProdutosQuery;
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
        final List<ProdutoResponse> produtosResponse = queryGateway
            .query(
                ProdutosQuery.builder().build(),
                ResponseTypes.multipleInstancesOf(ProdutoResponse.class))
            .join();

        return ResponseEntity.ok().body(produtosResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> produtosPorValor(@RequestParam("de") final BigDecimal valorDe,
                                                                  @RequestParam("ate") final BigDecimal valorAte) {
        final List<ProdutoResponse> produtosResponse = queryGateway
            .query(
                ProdutosPorValorQuery
                    .builder()
                    .valorDe(valorDe)
                    .valorAte(valorAte)
                    .build(),
                ResponseTypes.multipleInstancesOf(ProdutoResponse.class))
            .join();

        return ResponseEntity.ok().body(produtosResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> produtosPorQuantidade(@RequestParam("de") final Integer quantidadeDe,
                                                                       @RequestParam("ate") final Integer quantidadeAte) {
        final List<ProdutoResponse> produtosResponse = queryGateway
            .query(
                ProdutosPorQuantidadeQuery
                    .builder()
                    .quantidadeDe(quantidadeDe)
                    .quantidadeAte(quantidadeAte)
                    .build(),
                ResponseTypes.multipleInstancesOf(ProdutoResponse.class))
            .join();

        return ResponseEntity.ok().body(produtosResponse);
    }
}
