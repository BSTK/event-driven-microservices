package dev.bstk.msproduto.cqrscommand.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NovoProdutoCriadoEvent implements Serializable {

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
