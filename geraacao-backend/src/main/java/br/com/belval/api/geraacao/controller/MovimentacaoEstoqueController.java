package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.MovimentacaoEstoqueCreateDTO;
import br.com.belval.api.geraacao.dto.MovimentacaoEstoqueResponseDTO;
import br.com.belval.api.geraacao.service.MovimentacaoEstoqueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/movimentacaoestoque")
@CrossOrigin("http://localhost:5173")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movService;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueService movService) {
        this.movService = movService;
    }

    // Salvar movimentação
    @PostMapping
    public ResponseEntity<?> registrarSaidaEstoque(@Valid @RequestBody MovimentacaoEstoqueCreateDTO dto) {
        try {
            MovimentacaoEstoqueResponseDTO response = movService.registrarSaidaEstoque(
                    dto.getItemId(), dto.getInstituicaoId(), dto.getQuantidade(), dto.getTipoMovimentacao(), dto.getObservacao());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }

    // Busca movimentações por Instituição
    @GetMapping("/{idInstituicao}")
    public ResponseEntity<?> buscaPorInstituicao(@PathVariable Integer idInstituicao) {
        try {
            return ResponseEntity.ok(movService.BuscarPorInstituicao(idInstituicao));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Busca movimetações por Id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(movService.buscarPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

