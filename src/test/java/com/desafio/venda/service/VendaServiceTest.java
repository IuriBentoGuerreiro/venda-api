package com.desafio.venda.service;

import com.desafio.venda.dto.VendaRequest;
import com.desafio.venda.dto.VendaResponse;
import com.desafio.venda.dto.VendedorResumoQuery;
import com.desafio.venda.exception.NotFoundException;
import com.desafio.venda.model.Venda;
import com.desafio.venda.repository.VendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @Mock
    private VendaRepository vendaRepository;

    @InjectMocks
    private VendaService vendaService;

    @Test
    void deveSalvarVenda() {
        VendaRequest request = new VendaRequest(
                BigDecimal.valueOf(100),
                1,
                "João"
        );

        Venda venda = Venda.convert(request);

        when(vendaRepository.save(any())).thenReturn(venda);

        VendaResponse response = vendaService.save(request);

        assertNotNull(response);
        verify(vendaRepository).save(any());
    }

    @Test
    void deveRetornarVendaQuandoIdExistir() {
        Venda venda = new Venda();
        venda.setId(1);
        venda.setValor(BigDecimal.valueOf(100));
        venda.setDataVenda(LocalDateTime.now());

        when(vendaRepository.findById(1)).thenReturn(Optional.of(venda));

        var response = vendaService.findById(1);

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals(BigDecimal.valueOf(100), response.getValor());

        verify(vendaRepository).findById(1);
    }

    @Test
    void deveLancarExcecaoQuandoVendaNaoExistir() {
        when(vendaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            vendaService.findById(1);
        });
    }

    @Test
    void deveRetornarListaDeVendas() {
        Venda venda1 = new Venda();
        venda1.setId(1);
        venda1.setValor(BigDecimal.valueOf(100));
        venda1.setDataVenda(LocalDateTime.now());

        Venda venda2 = new Venda();
        venda2.setId(2);
        venda2.setValor(BigDecimal.valueOf(200));
        venda2.setDataVenda(LocalDateTime.now());

        when(vendaRepository.findAll()).thenReturn(List.of(venda1, venda2));

        var response = vendaService.findAll();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(1, response.get(0).getId());
        assertEquals(2, response.get(1).getId());
    }

    @Test
    void deveGerarRelatorioCorretamente() {
        LocalDateTime inicio = LocalDateTime.of(2026, 1, 1, 0, 0);
        LocalDateTime fim = LocalDateTime.of(2026, 1, 3, 0, 0);

        VendedorResumoQuery resumoMock = new VendedorResumoQuery(
                "João",
                3L,
                BigDecimal.valueOf(300)
        );

        when(vendaRepository.buscarResumo(any(), any()))
                .thenReturn(List.of(resumoMock));

        var response = vendaService.relatorio(inicio, fim);

        assertNotNull(response);
        assertEquals(1, response.size());

        var vendedor = response.get(0);

        assertEquals("João", vendedor.nome());
        assertEquals(3L, vendedor.totalVendas());
        assertEquals(0, vendedor.totalValor().compareTo(BigDecimal.valueOf(300)));

        assertEquals(0, vendedor.mediaDiaria().compareTo(BigDecimal.valueOf(100)));
    }

    @Test
    void deveLancarExcecaoQuandoPeriodoInvalido() {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.minusDays(1);

        assertThrows(IllegalArgumentException.class, () -> {
            vendaService.relatorio(inicio, fim);
        });
    }
}