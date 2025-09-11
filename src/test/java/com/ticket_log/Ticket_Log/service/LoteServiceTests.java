package com.ticket_log.Ticket_Log.service;

import com.ticket_log.Ticket_Log.domain.entity.Cartao;
import com.ticket_log.Ticket_Log.domain.entity.Lote;
import com.ticket_log.Ticket_Log.domain.repository.LoteRepository;
import com.ticket_log.Ticket_Log.dto.CartaoDesbloqueadoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoteServiceTests {

    @Mock
    private LoteRepository loteRepository;

    @InjectMocks
    private LoteService loteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarLote() {
        Lote lote = new Lote();
        when(loteRepository.save(lote)).thenReturn(lote);

        Lote result = loteService.criar(lote);

        assertNotNull(result);
        verify(loteRepository, times(1)).save(lote);
    }

    @Test
    void testListarTodos() {
        List<Lote> lotes = new ArrayList<>();
        when(loteRepository.findAll()).thenReturn(lotes);

        List<Lote> result = loteService.listarTodos();

        assertNotNull(result);
        assertEquals(lotes, result);
        verify(loteRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Encontrado() {
        Lote lote = new Lote();
        when(loteRepository.findById(1L)).thenReturn(Optional.of(lote));

        Lote result = loteService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(lote, result);
        verify(loteRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NaoEncontrado() {
        when(loteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> loteService.buscarPorId(1L));
        verify(loteRepository, times(1)).findById(1L);
    }

    @Test
    void testExcluir() {
        doNothing().when(loteRepository).deleteById(1L);

        loteService.excluir(1L);

        verify(loteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDesbloquearTodosCartoes() {
        Lote lote = new Lote();
        Cartao cartao = new Cartao();
        cartao.setStatus("Bloqueado");
        List<Cartao> cartoes = new ArrayList<>();
        cartoes.add(cartao);
        lote.setCartoes(cartoes);
        lote.setDesbloqueados(0);
        lote.setBloqueados(1);
        List<Lote> lotes = List.of(lote);

        when(loteRepository.findAll()).thenReturn(lotes);

        List<Lote> result = loteService.desbloquearTodosCartoes();

        assertNotNull(result);
        assertEquals("Desbloqueado", cartao.getStatus());
        verify(loteRepository, times(1)).findAll();
    }

    @Test
    void testDesbloquearCartoesDoLote_Encontrado() {
        Lote lote = new Lote();
        Cartao cartao = new Cartao();
        cartao.setStatus("Bloqueado");
        List<Cartao> cartoes = new ArrayList<>();
        cartoes.add(cartao);
        lote.setCartoes(cartoes);
        lote.setBloqueados(1);
        lote.setDesbloqueados(0);

        when(loteRepository.findById(1L)).thenReturn(Optional.of(lote));
        when(loteRepository.save(lote)).thenReturn(lote);

        Lote result = loteService.desbloquearCartoesDoLote(1L);

        assertNotNull(result);
        assertEquals("Desbloqueado", cartao.getStatus());
        verify(loteRepository, times(1)).findById(1L);
        verify(loteRepository, times(1)).save(lote);
    }

    @Test
    void testDesbloquearCartoesDoLote_NaoEncontrado() {
        when(loteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> loteService.desbloquearCartoesDoLote(1L));
        verify(loteRepository, times(1)).findById(1L);
    }

    @Test
    void testDesbloquearUmDoLote() {
        Lote lote = new Lote();
        Cartao cartao = new Cartao();
        cartao.setId(1L);
        cartao.setStatus("Bloqueado");
        List<Cartao> cartoes = new ArrayList<>();
        cartoes.add(cartao);
        lote.setCartoes(cartoes);
        lote.setDesbloqueados(0);
        lote.setBloqueados(1);

        when(loteRepository.findById(1L)).thenReturn(Optional.of(lote));
        when(loteRepository.save(lote)).thenReturn(lote);

        List<Long> cartaoIds = List.of(1L);
        List<CartaoDesbloqueadoDTO> result = loteService.desbloquearUmDoLote(1L, cartaoIds);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Desbloqueado", cartao.getStatus());
        verify(loteRepository, times(1)).findById(1L);
        verify(loteRepository, times(1)).save(lote);
    }

    @Test
    void testDesbloquearUmDoLote_CartaoNaoEncontrado() {
        Lote lote = new Lote();
        Cartao cartao = new Cartao();
        cartao.setId(2L);
        cartao.setStatus("Bloqueado");
        List<Cartao> cartoes = new ArrayList<>();
        cartoes.add(cartao);
        lote.setCartoes(cartoes);

        when(loteRepository.findById(1L)).thenReturn(Optional.of(lote));

        List<Long> cartaoIds = List.of(1L);

        assertThrows(EntityNotFoundException.class, () -> loteService.desbloquearUmDoLote(1L, cartaoIds));
        verify(loteRepository, times(1)).findById(1L);
    }
}
