package dev.bstk.msproduto.data;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepositoryQuery {

    List<Produto> produtosValorDeAte(final BigDecimal de, final BigDecimal ate);

    List<Produto> produtosQuantidadeDeAte(final Integer de, final Integer ate);
}
