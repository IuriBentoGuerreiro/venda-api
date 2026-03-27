package com.desafio.venda.dto;

import java.math.BigDecimal;

public record VendedorResumoQuery(
        String nome,
        Long totalVendas,
        BigDecimal totalValor
) {}