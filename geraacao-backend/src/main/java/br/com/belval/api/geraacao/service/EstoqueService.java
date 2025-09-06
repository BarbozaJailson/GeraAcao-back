package br.com.belval.api.geraacao.service;

import java.util.List;

import br.com.belval.api.geraacao.dto.EstoqueResponseDTO;

public interface EstoqueService {
    List<EstoqueResponseDTO> buscarEstoqueComItens(Integer idInstituicao);

    EstoqueResponseDTO buscarEstoquePorId(Integer id);
}
