package br.com.project.model.classes;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

@Audited
@Entity
@Table(name = "valor_comissao_filial")
@SequenceGenerator(name = "valor_comissao_filial_seq", sequenceName = "valor_comissao_filial_seq", initialValue = 1, allocationSize = 1)
public class ValorComissaoFuncionarioFilial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "valor_comissao_filial_seq")
	private Long val_codigo;

	@Basic
	@ManyToOne
	@JoinColumn(name = "val_comissao", nullable = false)
	@ForeignKey(name = "val_comissao_fk")
	private ComissaoFuncionarioFilial val_comissao = new ComissaoFuncionarioFilial();

	@Column(precision = 15, scale = 4, nullable = false)
	private BigDecimal val_valor = BigDecimal.ZERO;
	
	private String val_calculo;
	
	private String val_tipo;

	@Column(nullable = true, length = 100)
	private String val_descricao;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Long getVal_codigo() {
		return val_codigo;
	}

	public void setVal_codigo(Long val_codigo) {
		this.val_codigo = val_codigo;
	}

	public ComissaoFuncionarioFilial getVal_comissao() {
		return val_comissao;
	}

	public void setVal_comissao(ComissaoFuncionarioFilial val_comissao) {
		this.val_comissao = val_comissao;
	}

	public BigDecimal getVal_valor() {
		return val_valor;
	}

	public void setVal_valor(BigDecimal val_valor) {
		this.val_valor = val_valor;
	}

	public String getVal_descricao() {
		return val_descricao;
	}

	public void setVal_descricao(String val_descricao) {
		this.val_descricao = val_descricao;
	}

	public JSONObject getJson() {
		return new JSONObject(this);
	}
	

	public String getVal_calculo() {
		return val_calculo;
	}

	public void setVal_calculo(String val_calculo) {
		this.val_calculo = val_calculo;
	}

	public String getVal_tipo() {
		return val_tipo;
	}

	public void setVal_tipo(String val_tipo) {
		this.val_tipo = val_tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((val_codigo == null) ? 0 : val_codigo.hashCode());
		result = prime * result
				+ ((val_descricao == null) ? 0 : val_descricao.hashCode());
		result = prime * result
				+ ((val_valor == null) ? 0 : val_valor.hashCode());
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
		ValorComissaoFuncionarioFilial other = (ValorComissaoFuncionarioFilial) obj;
		if (val_codigo == null) {
			if (other.val_codigo != null)
				return false;
		} else if (!val_codigo.equals(other.val_codigo))
			return false;
		if (val_descricao == null) {
			if (other.val_descricao != null)
				return false;
		} else if (!val_descricao.equals(other.val_descricao))
			return false;
		if (val_valor == null) {
			if (other.val_valor != null)
				return false;
		} else if (!val_valor.equals(other.val_valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValorComissaoFuncionarioFilial [val_codigo=" + val_codigo
				+ ", val_valor=" + val_valor + ", val_descricao="
				+ val_descricao + "]";
	}

}
