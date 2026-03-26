package com.desafio.venda.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VendaRequest {

    private BigDecimal valor;

    private Integer vendedorId;
    private String vendedorNome;
}
