package br.com.belval.api.geraacao.service;

import br.com.belval.api.geraacao.dto.DoacaoCreateDTO;
import br.com.belval.api.geraacao.dto.DoacaoResponseDTO;
import br.com.belval.api.geraacao.dto.DoacaoUpdateDTO;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

public interface DoacaoService {

    DoacaoResponseDTO criarDoacao(DoacaoCreateDTO dto);

    DoacaoResponseDTO buscarPorId(Integer id);

    List<DoacaoResponseDTO> listarTodos();

    DoacaoResponseDTO atualizarDoacao(Integer id, DoacaoUpdateDTO dto);

    void excluir(Integer id);

    List<DoacaoResponseDTO> buscarPorDoador(@PathVariable Integer id);

    List<DoacaoResponseDTO> buscarPorInstituicao(@PathVariable Long idInstituicao);
}
