package dev.bstk.msproduto.cqrscommand.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExcluirProdutoCadastradoEvent implements Serializable {

    @NotNull
    private String produtoId;
}
