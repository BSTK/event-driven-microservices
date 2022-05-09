package dev.bstk.msproduto.cqrsquery.api;

import dev.bstk.msproduto.cqrsquery.api.response.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/produtos")
public class ProdutoQueryResource {

    private final CommandGateway commandGateway;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> produtos() {
        return ResponseEntity.ok().body(Collections.emptyList());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> produtosPorValor(@RequestParam("de") final BigDecimal valorDe,
                                                                  @RequestParam("ate") final BigDecimal valorAte) {
        return ResponseEntity.ok().body(Collections.emptyList());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> produtosPorQuantidade(@RequestParam("de") final Integer quantidadeDe,
                                                                       @RequestParam("ate") final Integer quantidadeAte) {
        return ResponseEntity.ok().body(Collections.emptyList());
    }
}
