package dev.bstk.msproduto.cqrscommand.rest.response;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CadastrarNovoProdutoResponse implements Serializable {

    @NotNull
    private UUID uuid;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer quantidade;

    @NotNull
    private LocalDateTime dataInclusao;

    @NotNull
    private LocalDateTime dataAtulizacao;
}
