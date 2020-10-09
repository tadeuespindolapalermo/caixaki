package br.com.project.model.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "bairro")
@SequenceGenerator(name = "bairro_seq", sequenceName = "bairro_seq", initialValue = 1, allocationSize = 1)
public class Bairro implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "bai_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bairro_seq")
	private Long bai_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "bai_descricao", principal = 1)
	@Column(length = 100, nullable = false)
	private String bai_descricao;

	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Long getBai_codigo() {
		return bai_codigo;
	}

	public void setBai_codigo(Long bai_codigo) {
		this.bai_codigo = bai_codigo;
	}

	public String getBai_descricao() {
		return bai_descricao;
	}

	public void setBai_descricao(String bai_descricao) {
		this.bai_descricao = bai_descricao;
	}


	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("bai_codigo", bai_codigo);
		map.put("bai_descricao", bai_descricao);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bai_codigo == null) ? 0 : bai_codigo.hashCode());
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
		Bairro other = (Bairro) obj;
		if (bai_codigo == null) {
			if (other.bai_codigo != null)
				return false;
		} else if (!bai_codigo.equals(other.bai_codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bairro [bai_codigo=" + bai_codigo + ", bai_descricao="
				+ bai_descricao + "]";
	}

}
