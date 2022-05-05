package dev.bstk.msproduto.cqrscommand.aggregate;

import dev.bstk.msproduto.cqrscommand.command.CadastrarNovoProdutoCommand;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

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

    public ProdutoAggregate(final CadastrarNovoProdutoCommand command) {
        this.uuid = command.getUuid();
        this.nome = command.getNome();
        this.valor = command.getValor();
        this.descricao = command.getDescricao();
        this.quantidade = command.getQuantidade();
    }
}
