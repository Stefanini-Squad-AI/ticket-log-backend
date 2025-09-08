package com.ticket_log.Ticket_Log.service;

import com.ticket_log.Ticket_Log.domain.entity.Cartao;
import com.ticket_log.Ticket_Log.domain.repository.CartaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartaoService {

    private final CartaoRepository repo;

    public CartaoService(CartaoRepository repo) {
        this.repo = repo;
    }

    public Cartao criar(Cartao cartao) {
        return repo.save(cartao);
    }

    public List<Cartao> listarTodos() {
        return repo.findAll();
    }

    public Cartao buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cartão não encontrado: " + id));
    }

    public void excluir(Long id) {
        repo.deleteById(id);
    }
}
