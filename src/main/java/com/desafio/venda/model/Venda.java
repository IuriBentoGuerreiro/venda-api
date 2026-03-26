package com.desafio.venda.model;

import com.desafio.venda.common.BaseEntity;
import com.desafio.venda.dto.VendaRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "venda")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Venda extends BaseEntity {

    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda;
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "vendedor_id", nullable = false)
    private Integer vendedorId;
    @Column(name = "vendedor_nome", nullable = false)
    private String vendedorNome;

    public static Venda convert(VendaRequest vendaRequest){
        return Venda.builder()
                .valor(vendaRequest.getValor())
                .dataVenda(LocalDateTime.now())
                .vendedorId(vendaRequest.getVendedorId())
                .vendedorNome(vendaRequest.getVendedorNome())
                .build();
    }
}
