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
@RequestMapping("/api/auth/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping
    public ResponseEntity<List<InstituicaoResponseDTO>> getAll() {
        instituicaoService.listarTodos();
        return ResponseEntity.ok(instituicaoService.listarTodos());
    }
    //Busca Instituição pelo Id
    @GetMapping("/{id}")
    public ResponseEntity <?> getById(@PathVariable Integer id){
            return ResponseEntity.ok(instituicaoService.buscarPorId(id));
    }
    //Salva Instituição
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@ModelAttribute @Valid InstituicaoCreateDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(instituicaoService.criarInstituicao(dto));
    }
    //Busca Instituição pelo CNPJ
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<?> getByCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(instituicaoService.buscarPorCnpj(cnpj));
    }
    //Atualiza Instituição pelo Id
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> atualizarInstituicao(
            @PathVariable Integer id,
            @ModelAttribute @Valid InstituicaoUpdateDTO dto) {
            return ResponseEntity.ok(instituicaoService.atualizarInstituicao(id, dto));
    }
    //Deleta Instituição pelo Id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
            instituicaoService.excluir(id);
            return ResponseEntity.noContent().build();
    }
}


