package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.*;
import br.com.belval.api.geraacao.model.RefreshToken;
import br.com.belval.api.geraacao.model.Usuario;
import br.com.belval.api.geraacao.repository.RefreshTokenRepository;
import br.com.belval.api.geraacao.security.JwtUtil;
import br.com.belval.api.geraacao.security.RefreshTokenService;
import br.com.belval.api.geraacao.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtUtil jwtUtil;
    //Salvar Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            Usuario usuarioEntity = usuarioService.loginEntity(dto.getLogin(), dto.getSenha(), dto.getCliente());
            UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(usuarioEntity);
            Integer instituicaoId = usuarioDTO.getInstituicaoId();
            String accessToken = jwtUtil.generateToken(
                    usuarioEntity.getEmail(),
                    usuarioEntity.getTipoUser().name(),
                    instituicaoId
            );
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuarioEntity);
            LoginResponseWithRefreshDTO response = new LoginResponseWithRefreshDTO(
                    accessToken, refreshToken.getToken(), usuarioDTO
            );
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

    //Salvar Usuario
    @PostMapping(value = "/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(@ModelAttribute @Valid UsuarioCreateDTO dto) {
        try {
            UsuarioResponseDTO usuario = usuarioService.criarUsuario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao registrar usuario: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        String refreshTokenStr = request.get("refreshToken");
        refreshTokenRepository.findByToken(refreshTokenStr)
                .ifPresent(rt -> refreshTokenService.deleteByUsuario(rt.getUsuario()));

        return ResponseEntity.ok(Map.of("message", "Logout realizado com sucesso"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshTokenStr = request.get("refreshToken");
        if (!refreshTokenService.isValid(refreshTokenStr)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Refresh token inválido ou expirado"));
        }
        Usuario usuario = refreshTokenRepository.findByToken(refreshTokenStr).get().getUsuario();
        Integer instituicaoId = usuario.getInstituicoes().isEmpty() ? null : usuario.getInstituicoes().get(0).getId();
        String newAccessToken = jwtUtil.generateToken(usuario.getEmail(), usuario.getTipoUser().name(), instituicaoId);
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }
}
