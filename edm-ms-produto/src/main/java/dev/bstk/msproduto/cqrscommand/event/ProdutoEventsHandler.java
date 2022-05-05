package dev.bstk.msproduto.cqrscommand.event;

import dev.bstk.msproduto.cqrscommand.data.Produto;
import dev.bstk.msproduto.cqrscommand.data.ProdutoRepository;
import dev.bstk.msproduto.util.Mapper;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProdutoEventsHandler {

    private final ProdutoRepository repository;

    @EventHandler
    public void on(final NovoProdutoCriadoEvent evento) {
        final Produto novoProdutoCadastrado = Mapper.to(evento, Produto.class);
        repository.save(novoProdutoCadastrado);
    }
}
