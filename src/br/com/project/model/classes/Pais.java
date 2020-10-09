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
@Table(name = "pais")
@SequenceGenerator(name = "pais_seq", sequenceName = "pais_seq", initialValue = 1, allocationSize = 1)
public class Pais implements Serializable {

	private static final long serialVersionUID = -3563399263999877340L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "pai_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_seq")
	private Long pai_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "pai_nome", principal = 1)
	@Column(nullable = false, length = 80)
	private String pai_nome;

	@Column(nullable = true, length = 15)
	private String pai_sigla;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Long getPai_id() {
		return pai_id;
	}

	public void setPai_id(Long pai_id) {
		this.pai_id = pai_id;
	}

	public String getPai_nome() {
		return pai_nome;
	}

	public void setPai_nome(String pai_nome) {
		this.pai_nome = pai_nome;
	}

	public String getPai_sigla() {
		return pai_sigla;
	}

	public void setPai_sigla(String pai_sigla) {
		this.pai_sigla = pai_sigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pai_id == null) ? 0 : pai_id.hashCode());
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
		Pais other = (Pais) obj;
		if (pai_id == null) {
			if (other.pai_id != null)
				return false;
		} else if (!pai_id.equals(other.pai_id))
			return false;
		return true;
	}

}
