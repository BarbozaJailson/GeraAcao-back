package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.CampanhaCreateDTO;
import br.com.belval.api.geraacao.dto.CampanhaResponseDTO;
import br.com.belval.api.geraacao.service.CampanhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/campanha")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;
    public ResponseEntity<List<CampanhaResponseDTO>> findAll(){
        List<CampanhaResponseDTO> campanhas = campanhaService.findAll();
        return ResponseEntity.ok(campanhas);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(Integer id) {
        try {
            CampanhaResponseDTO campanha = campanhaService.findById(id);
            return ResponseEntity.ok(campanha);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/instituicao/{instituicaoId}")
    public ResponseEntity<?> findByInstituicao(@Valid @PathVariable Integer instituicaoId) {
        try{
            List<CampanhaResponseDTO> campanhas = campanhaService.findByInstituicaoId(instituicaoId);
            return ResponseEntity.ok(campanhas);
        }catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CampanhaCreateDTO dto) {
        try{
            CampanhaResponseDTO campanha = campanhaService.criarCampanha(dto);
            return ResponseEntity.ok(campanha);
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
