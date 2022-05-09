package dev.bstk.msproduto.cqrscommand.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Produto p WHERE p.produtoId = :produtoId")
    boolean existeProdutoCadastrado(@Param("produtoId") final String produtoId);

    @Query("SELECT p FROM Produto p WHERE p.valor BETWEEN :de AND :ate")
    List<Produto> produtosPorValor(@Param("de") final BigDecimal de, @Param("ate") final BigDecimal ate);

    @Query("SELECT p FROM Produto p WHERE p.quantidade BETWEEN :de AND :ate")
    List<Produto> produtosPorQuantidade(@Param("de") final Integer de, @Param("ate") final Integer ate);
}
