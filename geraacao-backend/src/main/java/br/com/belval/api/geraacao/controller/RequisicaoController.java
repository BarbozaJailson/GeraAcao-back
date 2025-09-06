package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.RequisicaoCreateDTO;
import br.com.belval.api.geraacao.dto.RequisicaoResponseDTO;
import br.com.belval.api.geraacao.dto.RequisicaoUpdateDTO;
import br.com.belval.api.geraacao.service.RequisicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/requisicoes")
public class RequisicaoController {

    @Autowired
    private RequisicaoService requisicaoService;

    //Busca todas as Requisição
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<RequisicaoResponseDTO> requisicoes = requisicaoService.listarTodos();
            return ResponseEntity.ok(requisicoes);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }

    //Busca Requisições pelo Id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        try {
            RequisicaoResponseDTO findRequisicao = requisicaoService.buscarPorId(id);
            return ResponseEntity.ok(findRequisicao);
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    //Salvar Requisições
    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody RequisicaoCreateDTO dto) {
        try {
            RequisicaoResponseDTO requisicao = requisicaoService.criarRequisicao(dto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(requisicao);
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    //Atualizar Requisições pelo Id
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @Valid @RequestBody RequisicaoUpdateDTO dto) {
        try {
            RequisicaoResponseDTO requisicao = requisicaoService.atualizarRequisicao(id, dto);
            return ResponseEntity.ok(requisicao);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    //Deletar pelo Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            requisicaoService.excluir(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    //Buscar Requisição pela Instituição
    @GetMapping("/instituicao/{idInstituicao}")
    public ResponseEntity<?> listarRequisicaoPorInstituicao(@PathVariable Integer idInstituicao) {
        try{
            List<RequisicaoResponseDTO> requisicao = requisicaoService.findByInstituicaoId(idInstituicao);
            return ResponseEntity.ok(requisicao);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }
}

