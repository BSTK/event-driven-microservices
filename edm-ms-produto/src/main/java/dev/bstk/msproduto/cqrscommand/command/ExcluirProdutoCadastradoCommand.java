package dev.bstk.msproduto.cqrscommand.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class ExcluirProdutoCadastradoCommand implements Serializable {

    @NotNull
    @TargetAggregateIdentifier
    private String produtoId;
}
