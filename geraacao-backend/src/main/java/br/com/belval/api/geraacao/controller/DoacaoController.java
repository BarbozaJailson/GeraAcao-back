package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.DoacaoCreateDTO;
import br.com.belval.api.geraacao.dto.DoacaoResponseDTO;
import br.com.belval.api.geraacao.dto.DoacaoUpdateDTO;
import br.com.belval.api.geraacao.service.DoacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    //Fazer busca completa no banco de dados
    @GetMapping
    public ResponseEntity<List<DoacaoResponseDTO>> getAll() {
        List<DoacaoResponseDTO> doacoes = doacaoService.listarTodos();
        return ResponseEntity.ok(doacoes);
    }

    //Fazer busca por Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            DoacaoResponseDTO doacao = doacaoService.buscarPorId(id);
            return ResponseEntity.ok(doacao);
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // Salva registro
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody DoacaoCreateDTO dto) {
        try {
            DoacaoResponseDTO doacao = doacaoService.criarDoacao(dto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(doacao);
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    //Atualiza registro por Id
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody @Valid DoacaoUpdateDTO dto) {
        try {
            DoacaoResponseDTO doacao = doacaoService.atualizarDoacao(id, dto);
            return ResponseEntity.ok(doacao);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar doação: " + e.getMessage());
        }
    }

    //Deleta pelo Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            doacaoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e instanceof jakarta.persistence.EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar doacao: " + e.getMessage());
        }
    }

    //Busca doação pelo id do usuario
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<DoacaoResponseDTO>> getDoacoesByUsuario(@PathVariable Integer id) {
        List<DoacaoResponseDTO> doacoes = doacaoService.buscarPorDoador(id);
        if (doacoes.isEmpty()) {
            return ResponseEntity.noContent().build(); // retorna 204 se vazio
        }
        return ResponseEntity.ok(doacoes);
    }

    //Busca doação pela Instituição
    @GetMapping("/instituicao/{idInstituicao}")
    public ResponseEntity<List<DoacaoResponseDTO>> getDoacoesByInstituicao(@PathVariable Long idInstituicao) {
        List<DoacaoResponseDTO> doacoes = doacaoService.buscarPorInstituicao(idInstituicao);
        if (doacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(doacoes);
    }
}

