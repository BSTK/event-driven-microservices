package dev.bstk.msproduto.cqrscommand.rest;

import dev.bstk.msproduto.cqrscommand.command.CadastrarNovoProdutoCommand;
import dev.bstk.msproduto.cqrscommand.rest.request.CadastrarNovoProdutoRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/produtos")
public class ProdutoResource {

    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<Object> cadastrarNovoProduto(
        @RequestBody @Valid final CadastrarNovoProdutoRequest request) {

        final CadastrarNovoProdutoCommand command = CadastrarNovoProdutoCommand
            .builder()
            .produtoId(UUID.randomUUID())
            .nome(request.getNome())
            .valor(request.getValor())
            .descricao(request.getDescricao())
            .quantidade(request.getQuantidade())
            .build();

        commandGateway.sendAndWait(command);

        return ResponseEntity.ok().build();
    }
}
