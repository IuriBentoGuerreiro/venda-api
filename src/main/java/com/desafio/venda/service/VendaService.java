package com.desafio.venda.service;

import com.desafio.venda.dto.VendaRequest;
import com.desafio.venda.dto.VendaResponse;
import com.desafio.venda.dto.VendedorResumoResponse;
import com.desafio.venda.exception.NotFoundException;
import com.desafio.venda.model.Venda;
import com.desafio.venda.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;

    public VendaResponse save(VendaRequest vendaRequest) {
        return VendaResponse.convert(vendaRepository.save(Venda.convert(vendaRequest)));
    }

    public VendaResponse findById(Integer id) {
        return VendaResponse.convert(vendaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Venda não encontrada")
        ));
    }

    public List<VendaResponse> findAll() {
        return vendaRepository.findAll()
                .stream()
                .map(VendaResponse::convert)
                .toList();
    }

    public List<VendedorResumoResponse> relatorio(LocalDateTime inicio, LocalDateTime fim) {

        if (fim.isBefore(inicio)) {
            throw new IllegalArgumentException("Data fim não pode ser menor que início");
        }

        List<VendedorResumoResponse> resultados = vendaRepository.buscarResumo(inicio, fim);

        long dias = ChronoUnit.DAYS.between(
                inicio.toLocalDate(),
                fim.toLocalDate()
        ) + 1;

        if (dias <= 0) {
            throw new IllegalArgumentException("Período inválido");
        }

        return resultados.stream().map(r -> {

            BigDecimal media = r.mediaDiaria().divide(
                    BigDecimal.valueOf(dias),
                    2,
                    RoundingMode.HALF_UP
            );

            return new VendedorResumoResponse(
                    r.nome(),
                    r.totalVendas(),
                    media
            );

        }).toList();
    }
}
