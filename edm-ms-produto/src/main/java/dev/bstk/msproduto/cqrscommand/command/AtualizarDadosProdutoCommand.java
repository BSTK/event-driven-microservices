package dev.bstk.msproduto.cqrscommand.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class AtualizarDadosProdutoCommand implements Serializable {

    @NotNull
    @TargetAggregateIdentifier
    private String produtoId;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer quantidade;
}
