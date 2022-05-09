package dev.bstk.msproduto.cqrscommand.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Table(name = "PRODUTO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID")
    private String produtoId;

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

    @NotNull
    @Column(name = "DATA_INCLUSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @NotNull
    @Column(name = "DATA_ATULIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtulizacao;

    public Produto() { }

    @PrePersist
    private void prePersist() {
        setDataInclusao(Date.from(Instant.now()));
        setDataAtulizacao(Date.from(Instant.now()));
    }

    @PreUpdate
    private void preUpdate() {
        setDataAtulizacao(Date.from(Instant.now()));
    }
}
