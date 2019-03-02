package br.com.processos.model;

/**
 * Created by Jhon on 21/05/2015.
 */
public class Usuario {
    String login;
    String senha;


    public Usuario() {
        // TODO Auto-generated constructor stub
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
