package dev.bstk.msproduto.cqrscommand.aggregate;

import dev.bstk.msproduto.cqrscommand.command.AtualizarDadosProdutoCommand;
import dev.bstk.msproduto.cqrscommand.command.CadastrarNovoProdutoCommand;
import dev.bstk.msproduto.cqrscommand.command.ExcluirProdutoCadastradoCommand;
import dev.bstk.msproduto.cqrscommand.event.AtualizarProdutoEvent;
import dev.bstk.msproduto.cqrscommand.event.ExcluirProdutoCadastradoEvent;
import dev.bstk.msproduto.cqrscommand.event.NovoProdutoCadastradoEvent;
import dev.bstk.msproduto.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Validated
@Aggregate
public class ProdutoAggregate {

    @NotNull
    @AggregateIdentifier
    private String produtoId;

    public ProdutoAggregate() { }

    @CommandHandler
    public ProdutoAggregate(@Valid final CadastrarNovoProdutoCommand command) {
        log.info(">>> ProdutoAggregate -> CadastrarNovoProdutoCommand");
        AggregateLifecycle.apply(
            Mapper.to(command, NovoProdutoCadastradoEvent.class)
        );
    }

    @CommandHandler
    /// TODO: VERIFICAR QUAL MOTIVO (BUG) NÃO ESTÁ ATUALIZANDO
    public ProdutoAggregate(@Valid final AtualizarDadosProdutoCommand command) {
        log.info(">>> ProdutoAggregate -> AtualizarDadosProdutoCommand");
        AggregateLifecycle.apply(
            Mapper.to(command, AtualizarProdutoEvent.class)
        );
    }

    @CommandHandler
    /// TODO: VERIFICAR QUAL MOTIVO (BUG) NÃO ESTÁ EXCLUINDO
    public ProdutoAggregate(@Valid final ExcluirProdutoCadastradoCommand command) {
        log.info(">>> ProdutoAggregate -> ExcluirProdutoCadastradoCommand");
        AggregateLifecycle.apply(
            Mapper.to(command, ExcluirProdutoCadastradoEvent.class)
        );
    }

    @EventSourcingHandler
    public void on(final NovoProdutoCadastradoEvent evento) {
        this.produtoId = evento.getProdutoId();
    }

    @EventSourcingHandler
    /// TODO: VERIFICAR QUAL MOTIVO (BUG) ATUALIANDO
    public void on(final AtualizarDadosProdutoCommand evento) {
        this.produtoId = evento.getProdutoId();
    }

    @EventSourcingHandler
    /// TODO: VERIFICAR QUAL MOTIVO (BUG) NÃO ESTÁ EXCLUINDO
    public void on(final ExcluirProdutoCadastradoEvent evento) {
        this.produtoId = evento.getProdutoId();
    }
}
