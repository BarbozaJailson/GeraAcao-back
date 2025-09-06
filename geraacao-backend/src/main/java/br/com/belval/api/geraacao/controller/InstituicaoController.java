package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.InstituicaoCreateDTO;
import br.com.belval.api.geraacao.dto.InstituicaoResponseDTO;
import br.com.belval.api.geraacao.dto.InstituicaoUpdateDTO;
import br.com.belval.api.geraacao.service.InstituicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping
    public ResponseEntity<List<InstituicaoResponseDTO>> getAll() {
        List<InstituicaoResponseDTO> instituicoes = instituicaoService.listarTodos();
        return ResponseEntity.ok(instituicoes);
    }

    //Busca Instituição pelo Id
    @GetMapping("/{id}")
    public ResponseEntity <?> getById(@PathVariable Integer id){
        try {
            InstituicaoResponseDTO instituicao = instituicaoService.buscarPorId(id);
            return ResponseEntity.ok(instituicao);
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

    //Salva Instituição
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@ModelAttribute @Valid InstituicaoCreateDTO dto) {
        try {
            InstituicaoResponseDTO response = instituicaoService.criarInstituicao(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar instituição: " + e.getMessage());
        }
    }

    //Atualiza Instituição pelo Id
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> atualizarInstituicao(
            @PathVariable Integer id,
            @ModelAttribute @Valid InstituicaoUpdateDTO dto) {
        try {
            InstituicaoResponseDTO response = instituicaoService.atualizarInstituicao(id, dto);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar instituição: " + e.getMessage());
        }
    }

    //Deleta Instituição pelo Id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            instituicaoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e instanceof jakarta.persistence.EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar instituição: " + e.getMessage());
        }
    }

    //Busca Instituição pelo CNPJ
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<?> getByCnpj(@PathVariable String cnpj) {
        try {
            InstituicaoResponseDTO instituicao = instituicaoService.buscarPorCnpj(cnpj);
            return ResponseEntity.ok(instituicao);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor " + e.getMessage());
        }
    }

}


