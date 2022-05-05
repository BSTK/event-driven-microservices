package dev.bstk.msproduto.cqrscommand.data;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
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
    private LocalDateTime dataInclusao;

    @NotNull
    @Column(name = "DATA_ATULIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAtulizacao;

    public Produto() { }

    @PrePersist
    private void prePersist() {
        setDataInclusao(LocalDateTime.now());
        setDataAtulizacao(LocalDateTime.now());
    }

    @PreUpdate
    private void preUpdate() {
        setDataAtulizacao(LocalDateTime.now());
    }
}
