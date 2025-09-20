package br.com.belval.api.geraacao.dto;

public class LoginDTO {
    private String login; // pode ser "email" ou "cpf" — use o campo que quiser
    private String senha;

    // Getters e setters
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}
}