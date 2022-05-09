package dev.bstk.msproduto.cqrscommand.api;

import dev.bstk.msproduto.cqrscommand.command.AtualizarDadosProdutoCommand;
import dev.bstk.msproduto.cqrscommand.command.CadastrarNovoProdutoCommand;
import dev.bstk.msproduto.cqrscommand.command.ExcluirProdutoCadastradoCommand;
import dev.bstk.msproduto.cqrscommand.api.request.AtualizarDadosProdutoRequest;
import dev.bstk.msproduto.cqrscommand.api.request.CadastrarNovoProdutoRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/produtos")
public class ProdutoCommandResource {

    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<String> cadastrarNovoProduto(@RequestBody @Valid final CadastrarNovoProdutoRequest request) {
        final CadastrarNovoProdutoCommand command = CadastrarNovoProdutoCommand
            .builder()
            .produtoId(UUID.randomUUID().toString())
            .nome(request.getNome())
            .valor(request.getValor())
            .descricao(request.getDescricao())
            .quantidade(request.getQuantidade())
            .build();

        String response = commandGateway.sendAndWait(command);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<String> cadastrarNovoProduto(@RequestBody @Valid final AtualizarDadosProdutoRequest request) {
        final AtualizarDadosProdutoCommand command = AtualizarDadosProdutoCommand
            .builder()
            .produtoId(request.getProdutoId())
            .nome(request.getNome())
            .valor(request.getValor())
            .descricao(request.getDescricao())
            .quantidade(request.getQuantidade())
            .build();

        String response = commandGateway.sendAndWait(command);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<String> excluirProdutoCadastrado(@PathVariable("produtoId") final String produtoId) {
        final ExcluirProdutoCadastradoCommand command = ExcluirProdutoCadastradoCommand
            .builder()
            .produtoId(produtoId)
            .build();

        String response = commandGateway.sendAndWait(command);

        return ResponseEntity.ok().body(response);
    }
}
