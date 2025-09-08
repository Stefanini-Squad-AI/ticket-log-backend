package com.ticket_log.Ticket_Log.service;

import com.ticket_log.Ticket_Log.domain.entity.Cartao;
import com.ticket_log.Ticket_Log.domain.entity.Lote;
import com.ticket_log.Ticket_Log.domain.entity.MotivoBloqueio;
import com.ticket_log.Ticket_Log.domain.repository.LoteRepository;
import com.ticket_log.Ticket_Log.dto.CartaoDesbloqueadoDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class LoteService {

    private final LoteRepository repo;

    public LoteService(LoteRepository repo) {
        this.repo = repo;
    }

    public Lote criar(Lote lote) {
        return repo.save(lote);
    }

    public List<Lote> listarTodos() {
        return repo.findAll();
    }

    public Lote buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote não encontrado: " + id));
    }

    public void excluir(Long id) {
        repo.deleteById(id);
    }

    public List<Lote> desbloquearTodosCartoes() {
        List<Lote> lotes = repo.findAll();
        LocalDateTime agora = LocalDateTime.now();

        for (Lote lote : lotes) {
            // quais cartões deste lote ainda não foram desbloqueados?
            List<Cartao> bloqueados = lote.getCartoes().stream()
                    .filter(c -> c.getStatus().equals("Bloqueado"))
                    .toList();

            // marca todos com data de desbloqueio
            bloqueados.forEach(c -> c.setStatus("Desbloqueado"));

            // ajusta contadores
            int qtd = bloqueados.size();
            lote.setBloqueados(Math.max(0, lote.getBloqueados() - qtd));
            lote.setDesbloqueados(lote.getDesbloqueados() + qtd);
        }

        return lotes;
    }

    public Lote desbloquearCartoesDoLote(Long loteId) {
        Lote lote = repo.findById(loteId)
                .orElseThrow(() -> new EntityNotFoundException("Lote não encontrado: " + loteId));

        // Filtra só os cartões ainda bloqueados
        List<Cartao> bloqueados = lote.getCartoes().stream()
                .filter(c -> c.getStatus().equals("Bloqueado"))
                .toList();

        // Marca cada um com data de desbloqueio
        bloqueados.forEach(c -> c.setStatus("Desbloqueado"));

        // Ajusta contadores
        int qtd = bloqueados.size();
        lote.setBloqueados(Math.max(0, lote.getBloqueados() - qtd));
        lote.setDesbloqueados(lote.getDesbloqueados() + qtd);

        // Ajusta Motivo
        MotivoBloqueio motivoVazio = new MotivoBloqueio();
        motivoVazio.setId(0);
        motivoVazio.setDescricao("N/A");

        lote.setMotivoBloqueio(motivoVazio);

        // Salva e retorna
        return repo.save(lote);
    }

    public List<CartaoDesbloqueadoDTO> desbloquearUmDoLote(Long cdLote, List<Long> cartaoIds) {
        Lote lote = repo.findById(cdLote)
                .orElseThrow(() -> new EntityNotFoundException("Lote não encontrado: " + cdLote));
        List<CartaoDesbloqueadoDTO> result = new ArrayList<>();
        for (Long id : cartaoIds) {
            Cartao c = lote.getCartoes().stream()
                    .filter(x -> x.getId().equals(id) &&
                            "Bloqueado".equalsIgnoreCase(x.getStatus()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado: " + id));
            c.setStatus("Desbloqueado");
            lote.setDesbloqueados(lote.getDesbloqueados() + 1);
            lote.setBloqueados(lote.getBloqueados() - 1);
            result.add(new CartaoDesbloqueadoDTO(c.getNrCartao(), c.getIdentificacao()));
        }

        MotivoBloqueio motivoVazio = new MotivoBloqueio();
        motivoVazio.setId(0);
        motivoVazio.setDescricao("N/A");

        if(lote.getBloqueados() == 0) {
            lote.setMotivoBloqueio(motivoVazio);
        }

        repo.save(lote);
        return result;

    }
}
