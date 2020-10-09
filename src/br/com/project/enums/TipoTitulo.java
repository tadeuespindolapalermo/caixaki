package br.com.project.enums;

public enum TipoTitulo {

	RECEBER("Receber"), PAGAR("Pagar");

	private String tipo = "";

	private TipoTitulo(String tipo) {
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
