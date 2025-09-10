package br.com.belval.api.geraacao.dto;

import br.com.belval.api.geraacao.model.TipoMovimentacao;
import java.time.LocalDateTime;

public class MovimentacaoEstoqueResponseDTO {

    private Integer id;
    private int quantidade;
    private TipoMovimentacao tipoMovimentacao;
    private LocalDateTime dataMovimentacao;
    private String observacao;

    // Dados do Item relacionados
    private Integer itemId;
    private String itemNome;
    private String itemTamanho;
    private String itemGenero;
    private String itemSecao;
    private String itemTipo;
    private String itemImagem;

    // IDs opcionais para ligação com Requisição e Doação
    private Integer requisicaoId;
    private Integer doacaoId;
    private Integer instituicaoId;

    public MovimentacaoEstoqueResponseDTO() {}

    // Construtor que recebe a entidade para facilitar a conversão
    public MovimentacaoEstoqueResponseDTO(br.com.belval.api.geraacao.model.MovimentacaoEstoque mov) {
        this.id = mov.getId();
        this.quantidade = mov.getQuantidade();
        this.tipoMovimentacao = mov.getTipoMovimentacao();
        this.dataMovimentacao = mov.getDataMovimentacao();
        this.observacao = mov.getObservacao();

        if (mov.getItem() != null) {
            this.itemId = mov.getItem().getId();
            this.itemNome = mov.getItem().getNome();
            this.itemTamanho = mov.getItem().getTamanho();
            this.itemGenero = mov.getItem().getGenero();
            this.itemSecao = mov.getItem().getSecao();
            this.itemTipo = mov.getItem().getTipo();
            this.itemImagem = mov.getItem().getImagem();
        }

        if (mov.getRequisicao() != null) {
            this.requisicaoId = mov.getRequisicao().getId();
        }

        if (mov.getDoacao() != null) {
            this.doacaoId = mov.getDoacao().getId();
        }

        if (mov.getInstituicao() != null) {
            this.instituicaoId = mov.getInstituicao().getId();
        }

    }

    // getters e setters para todos os campos

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemNome() {
        return itemNome;
    }

    public void setItemNome(String itemNome) {
        this.itemNome = itemNome;
    }

    public String getItemTamanho() {
        return itemTamanho;
    }

    public void setItemTamanho(String itemTamanho) {
        this.itemTamanho = itemTamanho;
    }

    public String getItemGenero() {
        return itemGenero;
    }

    public void setItemGenero(String itemGenero) {
        this.itemGenero = itemGenero;
    }

    public String getItemSecao() {
        return itemSecao;
    }

    public void setItemSecao(String itemSecao) {
        this.itemSecao = itemSecao;
    }

    public String getItemTipo() {
        return itemTipo;
    }

    public void setItemTipo(String itemTipo) {
        this.itemTipo = itemTipo;
    }

    public String getItemImagem() {
        return itemImagem;
    }

    public void setItemImagem(String itemImagem) {
        this.itemImagem = itemImagem;
    }

    public Integer getRequisicaoId() {
        return requisicaoId;
    }

    public void setRequisicaoId(Integer requisicaoId) {
        this.requisicaoId = requisicaoId;
    }

    public Integer getDoacaoId() {
        return doacaoId;
    }

    public void setDoacaoId(Integer doacaoId) {
        this.doacaoId = doacaoId;
    }

    public Integer getInstituicaoId() {
        return instituicaoId;
    }

    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }
}


