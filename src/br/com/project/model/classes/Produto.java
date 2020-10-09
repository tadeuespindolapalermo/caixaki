package br.com.project.model.classes;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "produto")
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", initialValue = 1, allocationSize = 1)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "prd_codigo")
	@Id
	@Column(name = "prd_codigo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
	private Long prd_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "prd_descricao", principal = 1)
	@Column(nullable = false)
	private String prd_descricao;

	@IdentificaCampoPesquisa(descricaoCampo = "Comissão filial", campoConsulta = "prd_comissaofilial")
	@Column(scale = 4, precision = 15)
	private BigDecimal prd_comissaofilial = null;

	@Column(scale = 4, precision = 15)
	private BigDecimal prd_comissaousuario = null;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	private Boolean prd_comissaodiferefiliial;

	private Boolean prd_comissaodifereusuario;

	public Long getPrd_codigo() {
		return prd_codigo;
	}

	public void setPrd_codigo(Long prd_codigo) {
		this.prd_codigo = prd_codigo;
	}

	public String getPrd_descricao() {
		return prd_descricao;
	}

	public void setPrd_descricao(String prd_descricao) {
		this.prd_descricao = prd_descricao;
	}

	public BigDecimal getPrd_comissaofilial() {
		return prd_comissaofilial;
	}

	public void setPrd_comissaofilial(BigDecimal prd_comissaofilial) {
		this.prd_comissaofilial = prd_comissaofilial;
	}

	public BigDecimal getPrd_comissaousuario() {
		return prd_comissaousuario;
	}

	public void setPrd_comissaousuario(BigDecimal prd_comissaousuario) {
		this.prd_comissaousuario = prd_comissaousuario;
	}

	public Boolean getPrd_comissaodiferefiliial() {
		return prd_comissaodiferefiliial;
	}

	public void setPrd_comissaodiferefiliial(Boolean prd_comissaodiferefiliial) {
		this.prd_comissaodiferefiliial = prd_comissaodiferefiliial;
	}

	public Boolean getPrd_comissaodifereusuario() {
		return prd_comissaodifereusuario;
	}

	public void setPrd_comissaodifereusuario(Boolean prd_comissaodifereusuario) {
		this.prd_comissaodifereusuario = prd_comissaodifereusuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((prd_codigo == null) ? 0 : prd_codigo.hashCode());
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
		Produto other = (Produto) obj;
		if (prd_codigo == null) {
			if (other.prd_codigo != null)
				return false;
		} else if (!prd_codigo.equals(other.prd_codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [prd_codigo=" + prd_codigo + ", prd_descricao="
				+ prd_descricao + "]";
	}

}
