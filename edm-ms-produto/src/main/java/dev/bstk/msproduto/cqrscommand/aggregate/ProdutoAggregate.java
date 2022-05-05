package dev.bstk.msproduto.cqrscommand.aggregate;

import dev.bstk.msproduto.cqrscommand.command.CadastrarNovoProdutoCommand;
import dev.bstk.msproduto.cqrscommand.event.NovoProdutoCriadoEvent;
import dev.bstk.msproduto.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.ApplyMore;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Validated
@Aggregate
public class ProdutoAggregate {

    @NotNull
    @AggregateIdentifier
    private UUID uuid;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer quantidade;

    public ProdutoAggregate() { }

    public ProdutoAggregate(@Valid final CadastrarNovoProdutoCommand command) {
        final NovoProdutoCriadoEvent novoProdutoCriadoEvento = Mapper.to(command, NovoProdutoCriadoEvent.class);
        final ApplyMore aggregateApply = AggregateLifecycle.apply(novoProdutoCriadoEvento);

        aggregateApply.andThen(() -> log.info("Evento notificado = NovoProdutoCriadoEvent"));
    }

    @EventSourcingHandler
    public void on(final NovoProdutoCriadoEvent evento) {
        this.uuid = evento.getUuid();
        this.nome = evento.getNome();
        this.valor = evento.getValor();
        this.descricao = evento.getDescricao();
        this.quantidade = evento.getQuantidade();
    }
}
