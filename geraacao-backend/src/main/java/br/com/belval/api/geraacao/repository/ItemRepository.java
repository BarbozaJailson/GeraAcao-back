package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    //List<Item> findByInstituicaoId(Integer idInstituicao);

}
