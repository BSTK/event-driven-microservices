package dev.bstk.msproduto.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String>, ProdutoRepositoryQuery {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Produto p WHERE p.produtoId = :produtoId")
    boolean existeProdutoCadastrado(@Param("produtoId") final String produtoId);
}
