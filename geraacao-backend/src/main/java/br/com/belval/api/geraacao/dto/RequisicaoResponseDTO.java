package br.com.belval.api.geraacao.dto;

import java.time.LocalDate;

import br.com.belval.api.geraacao.model.Requisicao;

public class RequisicaoResponseDTO {

    // Dados Requisição
    private Integer id;
    private int quantidade;
    private LocalDate data;
    private String status;
    private int saldo;

    // Dados Instituicao
    private Integer instituicaoId;
    private String instituicaoNome;
    private String instituicaoCnpj;
    private String instituicaoLogradouro;
    private String instituicaoBairro;
    private String instituicaoCidade;
    private String instituicaoUf;
    private String instituicaoNumero;

    // Dados Item
    private Integer itemId;
    private String itemNome;
    private String itemTamanho;
    private String itemGenero;
    private String itemSecao;
    private String itemTipo;
    private String itemMaterial;
    private String itemDescricao;
    private String itemImagem;

    public RequisicaoResponseDTO(Requisicao requisicao) {
        this.id = requisicao.getId();
        this.quantidade = requisicao.getQuantidade();
        this.data = requisicao.getData();
        this.status = requisicao.getStatus();
        this.saldo = requisicao.getSaldo();
        if (requisicao.getInstituicao() != null) {
            this.instituicaoId = requisicao.getInstituicao().getId();
            this.instituicaoNome = requisicao.getInstituicao().getNome();
            this.instituicaoCnpj = requisicao.getInstituicao().getCnpj();
            this.instituicaoLogradouro = requisicao.getInstituicao().getLogradouro();
            this.instituicaoBairro = requisicao.getInstituicao().getBairro();
            this.instituicaoCidade = requisicao.getInstituicao().getCidade();
            this.instituicaoUf = requisicao.getInstituicao().getUf();
            this.instituicaoNumero = requisicao.getInstituicao().getNumero();
        }
        if (requisicao.getItem() != null) {
            this.itemId = requisicao.getItem().getId();
            this.itemNome = requisicao.getItem().getNome();
            this.itemTamanho = requisicao.getItem().getTamanho();
            this.itemGenero = requisicao.getItem().getGenero();
            this.itemSecao = requisicao.getItem().getSecao();
            this.itemTipo = requisicao.getItem().getTipo();
            this.itemMaterial = requisicao.getItem().getMaterial();
            this.itemDescricao = requisicao.getItem().getDescricao();
            this.itemImagem = requisicao.getItem().getImagem();
        }
    }

    public Integer getId() {
        return id;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public LocalDate getData() {
        return data;
    }
    public String getStatus() {
        return status;
    }
    public Integer getInstituicaoId() {
        return instituicaoId;
    }
    public String getInstituicaoNome() {
        return instituicaoNome;
    }
    public String getInstituicaoCnpj() {
        return instituicaoCnpj;
    }
    public String getInstituicaoLogradouro() {
        return instituicaoLogradouro;
    }
    public String getInstituicaoBairro() {
        return instituicaoBairro;
    }
    public String getInstituicaoCidade() {
        return instituicaoCidade;
    }
    public String getInstituicaoUf() {
        return instituicaoUf;
    }
    public String getInstituicaoNumero() {
        return instituicaoNumero;
    }
    public Integer getItemId() {
        return itemId;
    }
    public String getItemNome() {
        return itemNome;
    }
    public String getItemTamanho() {
        return itemTamanho;
    }
    public String getItemGenero() {
        return itemGenero;
    }
    public String getItemSecao() {
        return itemSecao;
    }
    public String getItemTipo() {
        return itemTipo;
    }
    public String getItemMaterial(){
        return itemMaterial;
    }
    public String getItemDescricao(){
        return itemDescricao;
    }
    public String getItemImagem() {
        return itemImagem;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }
    public void setInstituicaoNome(String instituicaoNome) {
        this.instituicaoNome = instituicaoNome;
    }
    public void setInstituicaoCnpj(String instituicaoCnpj) {
        this.instituicaoCnpj = instituicaoCnpj;
    }
    public void setInstituicaoLogradouro(String instituicaoLogradouro) {
        this.instituicaoLogradouro = instituicaoLogradouro;
    }
    public void setInstituicaoBairro(String instituicaoBairro) {
        this.instituicaoBairro = instituicaoBairro;
    }
    public void setInstituicaoCidade(String instituicaoCidade) {
        this.instituicaoCidade = instituicaoCidade;
    }
    public void setInstituicaoUf(String instituicaoUf) {
        this.instituicaoUf = instituicaoUf;
    }
    public void setInstituicaoNumero(String instituicaoNumero) {
        this.instituicaoNumero = instituicaoNumero;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    public void setItemNome(String itemNome) {
        this.itemNome = itemNome;
    }
    public void setItemTamanho(String itemTamanho) {
        this.itemTamanho = itemTamanho;
    }
    public void setItemGenero(String itemGenero) {
        this.itemGenero = itemGenero;
    }
    public void setItemSecao(String itemSecao) {
        this.itemSecao = itemSecao;
    }
    public void setItemTipo(String itemTipo) {
        this.itemTipo = itemTipo;
    }
    public void setItemMaterial(String itemMaterial){
        this.itemMaterial = itemMaterial;
    }
    public void setItemDescricao(String itemDescricao) {
        this.itemDescricao = itemDescricao;
    }
    public void setItemImagem(String itemImagem) {
        this.itemImagem = itemImagem;
    }
    public int getSaldo() {
        return saldo;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}


