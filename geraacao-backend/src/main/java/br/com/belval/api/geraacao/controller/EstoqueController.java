package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.EstoqueResponseDTO;
import br.com.belval.api.geraacao.service.EstoqueService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    //Busca estoque por Instituição
    @GetMapping("/instituicao/{idInstituicao}")
    public ResponseEntity<List<EstoqueResponseDTO>> listarEstoquePorInstituicao(@PathVariable Integer idInstituicao) {
        List<EstoqueResponseDTO> estoques = estoqueService.buscarEstoqueComItens(idInstituicao);
        return ResponseEntity.ok(estoques);
    }

    //Busca estoque pelo Id
    @GetMapping("/{idEstoque}")
    public ResponseEntity<?> buscaEstoqurPorId(@PathVariable Integer idEstoque){
        try{
            EstoqueResponseDTO estoque = estoqueService.buscarEstoquePorId(idEstoque);
            return ResponseEntity.ok(estoque);
        }catch(EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor");
        }
    }
}
