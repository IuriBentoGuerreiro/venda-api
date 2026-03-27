package com.desafio.venda.repository;

import com.desafio.venda.model.Venda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class VendaRepositoryTest {

    @Autowired
    private VendaRepository vendaRepository;

    @Test
    void deveRetornarResumoPorVendedor() {

        var inicio = LocalDateTime.of(2026, 1, 1, 0, 0);
        var fim = LocalDateTime.of(2026, 1, 3, 0, 0);

        Venda venda1 = new Venda();
        venda1.setValor(BigDecimal.valueOf(100));
        venda1.setDataVenda(LocalDateTime.of(2026, 1, 1, 10, 0));
        venda1.setVendedorId(1);
        venda1.setVendedorNome("João");

        Venda venda2 = new Venda();
        venda2.setValor(BigDecimal.valueOf(200));
        venda2.setDataVenda(LocalDateTime.of(2026, 1, 2, 15, 0));
        venda2.setVendedorId(1);
        venda2.setVendedorNome("João");

        vendaRepository.saveAll(List.of(venda1, venda2));

        var resultado = vendaRepository.buscarResumo(inicio, fim);

        assertEquals(1, resultado.size());

        var resumo = resultado.get(0);

        assertEquals("João", resumo.nome());
        assertEquals(2L, resumo.totalVendas());
        assertEquals(0, resumo.totalValor().compareTo(BigDecimal.valueOf(300)));

        long dias = ChronoUnit.DAYS.between(inicio.toLocalDate(), fim.toLocalDate()) + 1;
        BigDecimal mediaDiaria = resumo.totalValor()
                .divide(BigDecimal.valueOf(dias), 2, RoundingMode.HALF_UP);

        assertEquals(0, mediaDiaria.compareTo(BigDecimal.valueOf(100)));
    }
}