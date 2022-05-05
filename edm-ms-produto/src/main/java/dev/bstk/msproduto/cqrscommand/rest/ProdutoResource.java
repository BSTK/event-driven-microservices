package dev.bstk.msproduto.cqrscommand.rest;

import dev.bstk.msproduto.cqrscommand.command.CadastrarNovoProdutoCommand;
import dev.bstk.msproduto.cqrscommand.model.Produto;
import dev.bstk.msproduto.cqrscommand.rest.request.CadastrarNovoProdutoRequest;
import dev.bstk.msproduto.cqrscommand.rest.response.CadastrarNovoProdutoResponse;
import dev.bstk.msproduto.util.Mapper;
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
    public ResponseEntity<CadastrarNovoProdutoResponse> cadastrarNovoProduto(
        @RequestBody @Valid final CadastrarNovoProdutoRequest request) {

        final CadastrarNovoProdutoCommand command = CadastrarNovoProdutoCommand
            .builder()
            .uuid(UUID.randomUUID())
            .nome(request.getNome())
            .valor(request.getValor())
            .descricao(request.getDescricao())
            .quantidade(request.getQuantidade())
            .build();

        final Produto produtoCadastrado = commandGateway.sendAndWait(command);
        final CadastrarNovoProdutoResponse produtoCadastradoResponse = Mapper.to(produtoCadastrado, CadastrarNovoProdutoResponse.class);

        return ResponseEntity.ok().body(produtoCadastradoResponse);
    }
}
