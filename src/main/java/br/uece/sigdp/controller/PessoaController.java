package br.uece.sigdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.uece.sigdp.entity.dto.PessoaDTO;
import br.uece.sigdp.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Operation(summary = "Filtra pessoas", description = "Retorna uma lista paginada de pessoas baseada nos parâmetros de filtro fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pessoas encontrada",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PessoaDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
	@GetMapping("/filter")
    public ResponseEntity<Object> filter(
            @Parameter(description = "Direção da ordenação (ASC ou DESC)", example = "ASC") @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @Parameter(description = "Campo de ordenação", example = "id") @RequestParam(required = false, defaultValue = "id") String sortField,
            @Parameter(description = "Número da página (inicia em 0)", example = "0") @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "Tamanho da página", example = "10") @RequestParam(required = false, defaultValue = "10") int size) {

        Page<PessoaDTO> result = pessoaService.findByFilter(sortDirection, sortField, page, size);
        return ResponseEntity.ok(result);
    }

	

}
