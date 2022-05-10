package dev.bstk.msproduto.cqrscommand.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ProdutoRepositoryQueryImpl implements ProdutoRepositoryQuery {

    private static final String CAMPO_DE = "de";
    private static final String CAMPO_ATE = "ate";

    private static final String VALOR = "valor";
    private static final String QUANTIDADE = "quantidade";

    private static final String QUERY_DE = "SELECT p FROM Produto p WHERE p.%s <= :ate";
    private static final String QUERY_ATE = "SELECT p FROM Produto p WHERE p.%s >= :de";
    private static final String QUERY_DE_ATE = "SELECT p FROM Produto p WHERE p.%s BETWEEN :de AND :ate";


    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Produto> produtosValorDeAte(final BigDecimal de, final BigDecimal ate) {
        return executaQuery(VALOR, de, ate).getResultList();
    }

    @Override
    public List<Produto> produtosQuantidadeDeAte(final Integer de, final Integer ate) {
        return executaQuery(QUANTIDADE, de, ate).getResultList();
    }

    private TypedQuery<Produto> executaQuery(final String coluna, final Object de, final Object ate) {
        if (Objects.isNull(de)) {
            return manager
                .createQuery(String.format(QUERY_DE, coluna), Produto.class)
                .setParameter(CAMPO_ATE, ate);
        }

        if (Objects.isNull(ate)) {
            return manager
                .createQuery(String.format(QUERY_ATE, coluna), Produto.class)
                .setParameter(CAMPO_DE, de);
        }

        return manager
            .createQuery(String.format(QUERY_DE_ATE, coluna), Produto.class)
            .setParameter(CAMPO_DE, de)
            .setParameter(CAMPO_ATE, ate);
    }
}
