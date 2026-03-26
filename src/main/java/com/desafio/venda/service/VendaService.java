package com.desafio.venda.service;

import com.desafio.venda.dto.VendaRequest;
import com.desafio.venda.dto.VendaResponse;
import com.desafio.venda.model.Venda;
import com.desafio.venda.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                () -> new RuntimeException("Venda não encontrada")
        ));
    }

    public List<VendaResponse> findAll() {
        return vendaRepository.findAll()
                .stream()
                .map(VendaResponse::convert)
                .toList();
    }
}
