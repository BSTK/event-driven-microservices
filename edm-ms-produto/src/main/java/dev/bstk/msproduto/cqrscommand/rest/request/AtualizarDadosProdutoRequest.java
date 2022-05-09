package dev.bstk.msproduto.cqrscommand.rest.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AtualizarDadosProdutoRequest implements Serializable {

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
