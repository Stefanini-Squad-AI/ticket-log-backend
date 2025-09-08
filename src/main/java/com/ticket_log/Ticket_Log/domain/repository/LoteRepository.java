package com.ticket_log.Ticket_Log.domain.repository;

import com.ticket_log.Ticket_Log.domain.entity.Lote;
import com.ticket_log.Ticket_Log.domain.entity.LoteEmbosse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoteRepository {

    Lote save(Lote lote);
    Optional<Lote> findById(Long id);
    List<Lote> findAll();
    void deleteById(Long id);
}
