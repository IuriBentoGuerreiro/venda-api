package com.desafio.venda.controller;

import com.desafio.venda.dto.VendaRequest;
import com.desafio.venda.dto.VendaResponse;
import com.desafio.venda.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "VENDAS", description = "Endpoints responsáveis pelo gerenciamento de vendas")
@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @Operation(summary = "Criar uma nova venda", description = "Cadastra uma nova venda no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public VendaResponse save(@RequestBody @Valid VendaRequest vendaRequest) {
        return vendaService.save(vendaRequest);
    }

    @Operation(summary = "Buscar venda por ID", description = "Retorna os dados de uma venda específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda encontrada"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    })
    @GetMapping("/{id}")
    public VendaResponse findById(@PathVariable Integer id) {
        return vendaService.findById(id);
    }

    @Operation(summary = "Listar vendas", description = "Retorna uma lista com todas as vendas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vendas retornada com sucesso")
    })
    @GetMapping
    public List<VendaResponse> findAll() {
        return vendaService.findAll();
    }
}
