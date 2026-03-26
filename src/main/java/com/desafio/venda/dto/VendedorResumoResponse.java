package com.desafio.venda.dto;

import java.math.BigDecimal;

public record VendedorResumoResponse(
        String nome,
        Long totalVendas,
        BigDecimal mediaDiaria
) {
}
