package com.desafio.venda.model;

import com.desafio.venda.dto.VendaRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "venda")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda;
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "vendedor")
    private Vendedor vendedor;

    public static Venda convert(VendaRequest vendaRequest){
        return Venda.builder()
                .dataVenda(LocalDateTime.now())
                .valor(vendaRequest.getValor())
                .vendedor(vendaRequest.getVendedorId())
                .build();
    }
}
