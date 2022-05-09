package dev.bstk.msproduto.cqrscommand.aggregate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.msproduto.cqrscommand.command.AtualizarDadosProdutoCommand;
import dev.bstk.msproduto.cqrscommand.command.CadastrarNovoProdutoCommand;
import dev.bstk.msproduto.cqrscommand.command.ExcluirProdutoCadastradoCommand;
import dev.bstk.msproduto.cqrscommand.event.AtualizarProdutoEvent;
import dev.bstk.msproduto.cqrscommand.event.ExcluirProdutoCadastradoEvent;
import dev.bstk.msproduto.cqrscommand.event.NovoProdutoCadastradoEvent;
import dev.bstk.msproduto.util.Mapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Slf4j
@Validated
@Aggregate
public class ProdutoAggregate {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @NotNull
    @AggregateIdentifier
    private String produtoId;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Integer quantidade;

    private ProdutoAggregate() { }

    @CommandHandler
    public ProdutoAggregate(@Valid final CadastrarNovoProdutoCommand command) {
        log.info(">>> ProdutoAggregate -> CadastrarNovoProdutoCommand");
        AggregateLifecycle.apply(
            Mapper.to(command, NovoProdutoCadastradoEvent.class)
        );
    }

    @CommandHandler
    public ProdutoAggregate(@Valid final AtualizarDadosProdutoCommand command) {
        log.info(">>> ProdutoAggregate -> AtualizarDadosProdutoCommand");
        AggregateLifecycle.apply(
            Mapper.to(command, AtualizarProdutoEvent.class)
        );
    }

    @CommandHandler
    public ProdutoAggregate(@Valid final ExcluirProdutoCadastradoCommand command) {
        log.info(">>> ProdutoAggregate -> ExcluirProdutoCadastradoCommand");
        AggregateLifecycle.apply(
            Mapper.to(command, ExcluirProdutoCadastradoEvent.class)
        );
    }

    @EventSourcingHandler
    public void on(final NovoProdutoCadastradoEvent evento) {
        eventHandler(evento);
    }

    @EventSourcingHandler
    public void on(final AtualizarDadosProdutoCommand evento) {
        eventHandler(evento);
    }

    private void eventHandler(Object evento) {
        try {
            final String json = MAPPER.writeValueAsString(evento);
            final JsonNode jsonNode = MAPPER.readTree(json);

            setProdutoId(jsonNode.get("produtoId").asText());
            setNome(jsonNode.get("nome").asText());
            setValor(jsonNode.get("valor").decimalValue());
            setDescricao(jsonNode.get("descricao").asText());
            setQuantidade(jsonNode.get("quantidade").asInt());

        } catch (JsonProcessingException ex) {
            log.error("JsonProcessingException ex", ex);
        }
    }
}
