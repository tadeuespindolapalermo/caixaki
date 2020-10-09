package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "sendemail")
@SequenceGenerator(name = "sendemail_seq", sequenceName = "sendemail_seq", initialValue = 1, allocationSize = 1)
public class SendEmail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sendemail_seq")
	private Long id_sendemail;

	private String email;

	private String tratamento;

	private boolean enviado = false;

	@Column(columnDefinition = "text")
	private String msgEnviada;

	public void setMsgEnviada(String msgEnviada) {
		this.msgEnviada = msgEnviada;
	}

	public String getMsgEnviada() {
		return msgEnviada;
	}

	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}

	public String getTratamento() {
		return tratamento;
	}

	public Long getId_sendemail() {
		return id_sendemail;
	}

	public void setId_sendemail(Long id_sendemail) {
		this.id_sendemail = id_sendemail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnviado() {
		return enviado;
	}

	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	protected int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_sendemail == null) ? 0 : id_sendemail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SendEmail other = (SendEmail) obj;
		if (id_sendemail == null) {
			if (other.id_sendemail != null)
				return false;
		} else if (!id_sendemail.equals(other.id_sendemail))
			return false;
		return true;
	}

}
