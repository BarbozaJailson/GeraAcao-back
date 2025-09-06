package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByTipoUser(String tipoUser);
    List<Usuario> findByInstituicoes_Id(Integer idInstituicao);
}
