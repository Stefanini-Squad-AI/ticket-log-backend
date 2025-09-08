package com.ticket_log.Ticket_Log.infrastructure;

import com.ticket_log.Ticket_Log.domain.entity.Lote;
import com.ticket_log.Ticket_Log.domain.repository.LoteRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryLoteRepository implements LoteRepository {

    private final AtomicLong idGen = new AtomicLong(0);
    private final Map<Long, Lote> store = new ConcurrentHashMap<>();

    @Override
    public Lote save(Lote lote) {
        if (lote.getId() == null) {
            lote.setId(idGen.incrementAndGet());
        }
        store.put(lote.getId(), lote);
        return lote;
    }

    @Override
    public Optional<Lote> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Lote> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
