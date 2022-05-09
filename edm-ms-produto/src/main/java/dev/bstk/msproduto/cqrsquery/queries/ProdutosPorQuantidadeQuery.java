package dev.bstk.msproduto.cqrsquery.queries;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ProdutosPorQuantidadeQuery implements Serializable {

    private Integer de;
    private Integer ate;
}
