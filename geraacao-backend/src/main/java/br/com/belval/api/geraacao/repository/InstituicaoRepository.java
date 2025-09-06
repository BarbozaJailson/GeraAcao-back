package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer> {
    Optional<Instituicao> findByCnpj(String cnpj);
}
