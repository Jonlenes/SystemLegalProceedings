package br.com.processos.model;

/**
 * Created by Jhon on 24/05/2015.
 */
public class Advogado extends Pessoa {
    private Long regOAB;

    public Advogado() {
        // TODO Auto-generated constructor stub
    }

    public Advogado(String login, String senha, String nome, String telefone,
                    String email, String endereco, Long regOAB) {
        super(login, senha, nome, telefone, email, endereco);
        this.regOAB = regOAB;
    }

    public Long getRegOAB() {
        return regOAB;
    }

    public void setRegOAB(Long regOAB) {
        this.regOAB = regOAB;
    }

    @Override
    public String toString() {
        return regOAB + "-" + nome;
    }
}

