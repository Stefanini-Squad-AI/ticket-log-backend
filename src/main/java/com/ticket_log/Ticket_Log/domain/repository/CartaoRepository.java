package com.ticket_log.Ticket_Log.domain.repository;

import com.ticket_log.Ticket_Log.domain.entity.Cartao;

import java.util.List;
import java.util.Optional;

public interface CartaoRepository {
    Cartao save(Cartao cartao);
    Optional<Cartao> findById(Long id);
    List<Cartao> findAll();
    void deleteById(Long id);
}
