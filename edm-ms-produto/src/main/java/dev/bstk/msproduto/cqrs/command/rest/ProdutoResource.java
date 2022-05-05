package dev.bstk.msproduto.cqrs.command.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/produtos")
public class ProdutoResource {


    @PostMapping
    public ResponseEntity<Object> cadastrarNovoProduto() {
        return ResponseEntity.ok().build();
    }
}
