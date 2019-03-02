package process.model;

public class Solicitacao {
	private Long regOAB;
	private String requerente;
	private String dataSolicitacao;
	private String descricao;
	private String tipoAcao;
	
	public Solicitacao() {
		// TODO Auto-generated constructor stub
	}

	public Solicitacao(Long regOAB, String requerente, String dataSolicitacao,
			String descricao, String tipoAcao) {
		super();
		this.regOAB = regOAB;
		this.requerente = requerente;
		this.dataSolicitacao = dataSolicitacao;
		this.descricao = descricao;
		this.tipoAcao = tipoAcao;
	}

	public Long getRegOAB() {
		return regOAB;
	}

	public void setRegOAB(Long regOAB) {
		this.regOAB = regOAB;
	}

	public String getRequerente() {
		return requerente;
	}

	public void setRequerente(String requerente) {
		this.requerente = requerente;
	}

	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(String tipoAcao) {
		this.tipoAcao = tipoAcao;
	}
}
