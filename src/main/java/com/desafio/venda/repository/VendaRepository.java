package com.desafio.venda.repository;

import com.desafio.venda.dto.VendedorResumoQuery;
import com.desafio.venda.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    @Query("""
                   SELECT new com.desafio.venda.dto.VendedorResumoQuery(
                       v.vendedorNome,
                       COUNT(v),
                       SUM(v.valor)
                   )
                   FROM Venda v
                   WHERE v.dataVenda BETWEEN :inicio AND :fim
                   GROUP BY v.vendedorNome
            """)
    List<VendedorResumoQuery> buscarResumo(LocalDateTime inicio, LocalDateTime fim);
}
