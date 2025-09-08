package com.ticket_log.Ticket_Log.infrastructure;

import com.ticket_log.Ticket_Log.domain.entity.Cartao;
import com.ticket_log.Ticket_Log.domain.repository.CartaoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCartaoRepository implements CartaoRepository {

    private final AtomicLong idGen = new AtomicLong(0);
    private final Map<Long, Cartao> store = new ConcurrentHashMap<>();

    @Override
    public Cartao save(Cartao cartao) {
        if (cartao.getId() == null) {
            cartao.setId(idGen.incrementAndGet());
        }
        store.put(cartao.getId(), cartao);
        return cartao;
    }

    @Override
    public Optional<Cartao> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Cartao> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
