package br.com.processos.model;

/**
 * Created by Jhon on 24/05/2015.
 */
public class TipoAcao {

    private String tipo;
    private String descricao;

    public TipoAcao() {
        // TODO Auto-generated constructor stub
    }

    public TipoAcao(String tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
