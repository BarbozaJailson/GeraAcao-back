package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.LoginDTO;
import br.com.belval.api.geraacao.dto.LoginResponseDTO;
import br.com.belval.api.geraacao.dto.UsuarioResponseDTO;
import br.com.belval.api.geraacao.model.Usuario;
import br.com.belval.api.geraacao.security.JwtUtil;
import br.com.belval.api.geraacao.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;
    //Salvar Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            // 1. Valida login e senha -> retorna entity
            Usuario usuarioEntity = usuarioService.loginEntity(dto.getLogin(), dto.getSenha());

            // 2. Converte entity para DTO
            UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(usuarioEntity);

            // 3. Pega a instituição principal (primeira da lista)
            Integer instituicaoId = usuarioDTO.getInstituicaoId();

            // 4. Gera token JWT com email, tipoUser e instituicaoId
            String token = jwtUtil.generateToken(
                    usuarioEntity.getEmail(),
                    usuarioEntity.getTipoUser().name(), // converte enum para String
                    instituicaoId
            );

            // 5. Retorna token + DTO completo
            LoginResponseDTO response = new LoginResponseDTO(token, usuarioDTO);
            return ResponseEntity.ok(response);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao processar login: " + e.getMessage());
        }
    }

}
