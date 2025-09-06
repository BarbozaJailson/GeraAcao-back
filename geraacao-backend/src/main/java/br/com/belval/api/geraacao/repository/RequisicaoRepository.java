package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Integer> {
    List<Requisicao> findByInstituicaoId(Integer idInstituicao);
}
