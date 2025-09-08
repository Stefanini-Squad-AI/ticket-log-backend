package com.ticket_log.Ticket_Log.controller;

import com.ticket_log.Ticket_Log.domain.entity.Lote;
import com.ticket_log.Ticket_Log.dto.CartaoDesbloqueadoDTO;
import com.ticket_log.Ticket_Log.service.LoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@CrossOrigin(origins = "http://localhost:4200")
public class LoteCartaoController {

    private final LoteService service;

    public LoteCartaoController(LoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Lote> getAll() {
        return service.listarTodos();
    }

    @GetMapping("/{cdLote}")
    public Lote getOne(@PathVariable Long cdLote) {
        return service.buscarPorId(cdLote);
    }

    @PostMapping
    public Lote create(@RequestBody Lote lote) {
        return service.criar(lote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/desbloquearTodos")
    public ResponseEntity<List<Lote>> desbloquearTodos() {
        return ResponseEntity.ok(service.desbloquearTodosCartoes());
    }

    @PutMapping("/desbloquear/{cdLote}")
    public ResponseEntity<Void> desbloquear(@PathVariable Long cdLote) {
        service.desbloquearCartoesDoLote(cdLote);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/desbloquearOne/{cdLote}")
    public ResponseEntity<List<CartaoDesbloqueadoDTO>> desbloquearUm(
            @PathVariable Long cdLote,
            @RequestBody List<Long> cartaoIds
    ) {
        List<CartaoDesbloqueadoDTO> desbloqueados =
                service.desbloquearUmDoLote(cdLote, cartaoIds);
        return ResponseEntity.ok(desbloqueados);
    }
}
