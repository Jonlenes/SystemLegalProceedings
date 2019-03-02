package process.model;

public class AdvogadoRank {
	Long regOAB;
	String nome;
	int qtdeProc;
	int posRank;

	public AdvogadoRank() {
	}

	public AdvogadoRank(Long regOAB, String nome, int qtdeProc, int posRank) {
		this.regOAB = regOAB;
		this.nome = nome;
		this.qtdeProc = qtdeProc;
		this.posRank = posRank;
	}
	
	public Long getRegOAB() {
		return regOAB;
	}

	public void setRegOAB(Long regOAB) {
		this.regOAB = regOAB;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdeProc() {
		return qtdeProc;
	}

	public void setQtdeProc(int qtdeProc) {
		this.qtdeProc = qtdeProc;
	}
	
	public int getPosRank() {
		return posRank;
	}

	public void setPosRank(int posRank) {
		this.posRank = posRank;
	}
}
