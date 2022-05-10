package dev.bstk.msproduto.cqrscommand.domain.event;

import dev.bstk.msproduto.data.Produto;
import dev.bstk.msproduto.data.ProdutoRepository;
import dev.bstk.msproduto.util.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ProdutoEventsHandler {

    private final ProdutoRepository repository;

    @EventHandler
    public void on(final NovoProdutoCadastradoEvent evento) {
        final Produto novoProdutoCadastrado = Mapper.to(evento, Produto.class);
        final Produto novoProdutoCadastradoSalvo = repository.save(novoProdutoCadastrado);

        log.info("Enviar para fila/email [ {} ] ", novoProdutoCadastradoSalvo);
    }

    @EventHandler
    public void on(final AtualizarProdutoEvent evento) {
        final Produto produtoAtualizado = Mapper.to(evento, Produto.class);
        final Produto produtoAtualizadoSalvo = repository.save(produtoAtualizado);

        log.info("Enviar para fila/email [ {} ] ", produtoAtualizadoSalvo);
    }

    @EventHandler
    public void on(final ExcluirProdutoCadastradoEvent evento) {
        boolean existeProdutoCadastrado = repository.existeProdutoCadastrado(evento.getProdutoId());

        if (!existeProdutoCadastrado) {
            log.warn("Não existe produto com id = [ {} ]", evento.getProdutoId());
            return;
        }

        repository.deleteById(evento.getProdutoId());
        log.info("Excluindo produto [ {} ] ", evento.getProdutoId());
    }
}
