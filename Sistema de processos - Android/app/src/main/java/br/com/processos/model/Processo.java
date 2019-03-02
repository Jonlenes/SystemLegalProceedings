package br.com.processos.model;

/**
 * Created by Jhon on 22/05/2015.
 */
public class Processo {
    private Long numero;
    private String requerente;
    private String requerido;
    private Long regAdvogado;
    private String dataInicial;
    private String dataFinal;
    private String tipo;
    private int status;
    private String descricao;

    public Processo(){

    }

    public Processo(Long numero, String requerente, String requerido, Long regAdvogado, String dataInicial, String dataFinal, int status, String tipo, String descricao) {
        this.numero = numero;
        this.requerente = requerente;
        this.requerido = requerido;
        this.regAdvogado = regAdvogado;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.status = status;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getRequerente() {
        return requerente;
    }

    public void setRequerente(String requerente) {
        this.requerente = requerente;
    }

    public Long getRegAdvogado() {
        return regAdvogado;
    }

    public void setRegAdvogado(Long regAdvogado) {
        this.regAdvogado = regAdvogado;
    }

    public String getRequerido() {
        return requerido;
    }

    public void setRequerido(String requerido) {
        this.requerido = requerido;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return numero + "\t\t" + dataInicial;
    }
}