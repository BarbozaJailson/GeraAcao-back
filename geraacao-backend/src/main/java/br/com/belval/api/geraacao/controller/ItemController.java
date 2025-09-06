package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.ItemCreateDTO;
import br.com.belval.api.geraacao.dto.ItemResponseDTO;
import br.com.belval.api.geraacao.dto.ItemUpdateDTO;
import br.com.belval.api.geraacao.service.ItemService;
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
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //Fazer busca completa no banco de dados
    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAll() {
        List<ItemResponseDTO> itens = itemService.listarTodos();

        return ResponseEntity.ok(itens);
    }

    //Buscar Item pelo Id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        try {
            ItemResponseDTO item = itemService.buscarPorId(id);
            return ResponseEntity.ok(item);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    //Inserir Item no banco
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> salvarItem(@ModelAttribute @Valid ItemCreateDTO dto) {
        try {
            ItemResponseDTO item = itemService.criarItem(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(item);
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    //Atualizar Item pelo Id
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> atualizarItem(@PathVariable Integer id, @ModelAttribute @Valid ItemUpdateDTO dto) {
        try {
            ItemResponseDTO item = itemService.atualizarItem(id, dto);
            return ResponseEntity.ok(item);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    //Deletar Item pelo Id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            itemService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }
}

