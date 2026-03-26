package com.desafio.venda.controller;

import com.desafio.venda.dto.VendaRequest;
import com.desafio.venda.dto.VendaResponse;
import com.desafio.venda.dto.VendedorResumoResponse;
import com.desafio.venda.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "VENDAS", description = "Endpoints responsáveis pelo gerenciamento de vendas")
@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @Operation(summary = "Criar uma nova venda", description = "Cadastra uma nova venda no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venda criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<VendaResponse> save(@RequestBody @Valid VendaRequest vendaRequest) {
        VendaResponse vendaResponse = vendaService.save(vendaRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(vendaResponse);
    }

    @Operation(summary = "Buscar venda por ID", description = "Retorna os dados de uma venda específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda encontrada"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VendaResponse> findById(@PathVariable Integer id) {
        VendaResponse vendaResponse = vendaService.findById(id);

        return ResponseEntity.ok().body(vendaResponse);
    }

    @Operation(summary = "Listar vendas", description = "Retorna uma lista com todas as vendas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vendas retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<VendaResponse>> findAll() {
        List<VendaResponse> vendaResponse = vendaService.findAll();

        return ResponseEntity.ok().body(vendaResponse);
    }

    @Operation(
            summary = "Relatório de vendas por vendedor",
            description = "Retorna o total de vendas e a média diária por vendedor dentro de um período"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Período inválido")
    })
    @GetMapping("/vendedores")
    public ResponseEntity<List<VendedorResumoResponse>> relatorio(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataFim
    ) {

        List<VendedorResumoResponse> response =
                vendaService.relatorio(dataInicio, dataFim);

        return ResponseEntity.ok(response);
    }
}
