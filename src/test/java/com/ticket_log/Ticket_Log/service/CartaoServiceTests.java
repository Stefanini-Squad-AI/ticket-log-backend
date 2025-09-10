package com.ticket_log.Ticket_Log.service;

import com.ticket_log.Ticket_Log.domain.entity.Cartao;
import com.ticket_log.Ticket_Log.domain.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CartaoServiceTests {


    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CartaoService cartaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriar() {
        Cartao cartao = new Cartao();
        cartao.setId(1L);
        cartao.setNome("Cartão Teste");

        when(cartaoRepository.save(cartao)).thenReturn(cartao);

        Cartao result = cartaoService.criar(cartao);

        assertNotNull(result);
        assertEquals(cartao.getId(), result.getId());
        assertEquals(cartao.getNome(), result.getNome());
        verify(cartaoRepository, times(1)).save(cartao);
    }

    @Test
    void testListarTodos() {
        Cartao cartao1 = new Cartao();
        cartao1.setId(1L);
        cartao1.setNome("Cartão 1");

        Cartao cartao2 = new Cartao();
        cartao2.setId(2L);
        cartao2.setNome("Cartão 2");

        List<Cartao> cartoes = Arrays.asList(cartao1, cartao2);

        when(cartaoRepository.findAll()).thenReturn(cartoes);

        List<Cartao> result = cartaoService.listarTodos();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(cartaoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Encontrado() {
        Cartao cartao = new Cartao();
        cartao.setId(1L);
        cartao.setNome("Cartão Teste");

        when(cartaoRepository.findById(1L)).thenReturn(Optional.of(cartao));

        Cartao result = cartaoService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(cartao.getId(), result.getId());
        assertEquals(cartao.getNome(), result.getNome());
        verify(cartaoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NaoEncontrado() {
        when(cartaoRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            cartaoService.buscarPorId(1L);
        });

        assertEquals("Cartão não encontrado: 1", exception.getMessage());
        verify(cartaoRepository, times(1)).findById(1L);
    }

    @Test
    void testExcluir() {
        Long id = 1L;

        doNothing().when(cartaoRepository).deleteById(id);

        cartaoService.excluir(id);

        verify(cartaoRepository, times(1)).deleteById(id);
    }

}
