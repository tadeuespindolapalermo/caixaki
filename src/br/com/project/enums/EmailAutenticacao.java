package br.com.project.enums;

public enum EmailAutenticacao {

	TIPO_EMAIL_AUTENTICACAO_PLAIN("Plain"), TIPO_EMAIL_AUTENTICACAO_GSSAPI(
			"Gssapi"), TIPO_EMAIL_AUTENTICACAO_NTLM("Ntlm"), TIPO_EMAIL_AUTENTICACAO_DIGEST(
			"Digest"), TIPO_EMAIL_AUTENTICACAO_CRAM("Cram"), TIPO_EMAIL_AUTENTICACAO_LOGIN(
			"Login"), TIPO_EMAIL_AUTENTICACAO_POP("Pop");

	private String tipo = "";

	private EmailAutenticacao(String tipo) {
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
