package com.desafio.venda.dto;

import com.desafio.venda.model.Venda;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class VendaResponse {

    private Integer id;
    private LocalDateTime dataVenda;
    private BigDecimal valor;

    private Vendedor vendedor;

    public static VendaResponse convert(Venda venda){
        return VendaResponse.builder()
                .id(venda.getId())
                .dataVenda(venda.getDataVenda())
                .valor(venda.getValor())
                .vendedor(venda.getVendedorId())
                .build();
    }
}
