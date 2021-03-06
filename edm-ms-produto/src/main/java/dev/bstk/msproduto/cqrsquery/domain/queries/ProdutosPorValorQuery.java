package dev.bstk.msproduto.cqrsquery.domain.queries;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class ProdutosPorValorQuery implements Serializable {

    private BigDecimal de;
    private BigDecimal ate;
}
