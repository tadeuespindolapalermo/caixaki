package br.com.project.enums;

public enum SegurancaEmail {

	TIPO_EMAIL_SEGURANCA_SEM_CRIPTOGRAFIA("Sem criptografia"), TIPO_EMAIL_SEGURANCA_TLS(
			"Segurança tls"), TIPO_EMAIL_SEGURANCA_SSL("Segurança ssl");

	private String tipo = "";

	private SegurancaEmail(String tipo) {
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
