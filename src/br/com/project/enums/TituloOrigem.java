package br.com.project.enums;

public enum TituloOrigem {

	COMISSAO("Comissão"), ORIGEM("Origem");

	private String tipo = "";

	private TituloOrigem(String tipo) {
		this.tipo = tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return getTipo();
	}

}
