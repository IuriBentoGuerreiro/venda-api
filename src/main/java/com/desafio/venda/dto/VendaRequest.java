package com.desafio.venda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VendaRequest {

    private BigDecimal valor;

    private Integer vendedorId;
    private String vendedorNome;
}
