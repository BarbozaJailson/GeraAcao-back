package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.dto.CampanhaResponseDTO;
import br.com.belval.api.geraacao.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
    List<Campanha> findByInstituicaoId(Integer instituicaoId);
}
