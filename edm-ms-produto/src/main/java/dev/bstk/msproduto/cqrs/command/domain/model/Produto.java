package dev.bstk.msproduto.cqrs.command.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "PRODUTO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "UUID")
    private UUID uuid;

    @NotNull
    @Column(name = "SKU")
    private String sku;

    @NotNull
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;

    @NotNull
    @Column(name = "QUANTIDADE")
    private Integer quantidade;
}
