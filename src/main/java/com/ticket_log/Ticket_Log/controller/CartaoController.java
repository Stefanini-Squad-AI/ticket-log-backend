package com.ticket_log.Ticket_Log.controller;

import com.ticket_log.Ticket_Log.domain.entity.Cartao;
import com.ticket_log.Ticket_Log.service.CartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartoes")
@CrossOrigin(origins = "http://18.217.121.166:83")
public class CartaoController {

    private final CartaoService service;

    public CartaoController(CartaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cartao> getAll() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Cartao getOne(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Cartao create(@RequestBody Cartao cartao) {
        return service.criar(cartao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
