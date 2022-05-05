package dev.bstk.msproduto.cqrscommand.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class CadastrarNovoProdutoCommand implements Serializable {

    @NotNull
    @TargetAggregateIdentifier
    private UUID produtoId;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer quantidade;
}
