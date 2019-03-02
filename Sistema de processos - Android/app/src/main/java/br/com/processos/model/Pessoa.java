package br.com.processos.model;

/**
 * Created by Jhon on 21/05/2015.
 */
public class Pessoa extends Usuario{
    String nome;
    String telefone;
    String email;
    String endereco;

    public Pessoa() {
        // TODO Auto-generated constructor stub
    }

    public Pessoa(String login, String senha, String nome, String telefone, String email, String endereco) {
        super(login, senha);
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
