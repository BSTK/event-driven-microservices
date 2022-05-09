package dev.bstk.msproduto.cqrscommand.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarProdutoEvent implements Serializable {

    @NotNull
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
