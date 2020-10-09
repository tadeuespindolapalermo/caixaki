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

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "serie")
@SequenceGenerator(name = "serie_seq", sequenceName = "serie_seq", initialValue = 1, allocationSize = 1)
public class Serie implements Serializable {

	private static final long serialVersionUID = 6650683358477333875L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "ser_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serie_seq")
	private Long ser_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "ser_descricao", principal = 1)
	@Column(length = 100, nullable = false)
	private String ser_descricao;

	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}
	
	public Long getSer_codigo() {
		return ser_codigo;
	}

	public void setSer_codigo(Long ser_codigo) {
		this.ser_codigo = ser_codigo;
	}

	public String getSer_descricao() {
		return ser_descricao;
	}

	public void setSer_descricao(String ser_descricao) {
		this.ser_descricao = ser_descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ser_codigo == null) ? 0 : ser_codigo.hashCode());
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
		Serie other = (Serie) obj;
		if (ser_codigo == null) {
			if (other.ser_codigo != null)
				return false;
		} else if (!ser_codigo.equals(other.ser_codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Serie [ser_codigo=" + ser_codigo + ", ser_descricao="
				+ ser_descricao + "]";
	}

}
