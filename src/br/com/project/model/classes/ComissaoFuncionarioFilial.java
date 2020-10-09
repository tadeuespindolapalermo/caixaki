package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.enums.TipoCadastro;

@Audited
@Entity
@Table(name = "comissao_funcionario_filial")
@SequenceGenerator(name = "comissao_funcionario_filial_seq", sequenceName = "comissao_funcionario_filial_seq", initialValue = 1, allocationSize = 1)
public class ComissaoFuncionarioFilial implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "com_codigo", principal = 2)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comissao_funcionario_filial_seq")
	private Long com_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Filial", campoConsulta = "fil_codigo.fil_descricao", principal = 4)
	@Basic
	@ManyToOne
	@JoinColumn(name = "fil_codigo", nullable = false)
	@ForeignKey(name = "fil_codigo_fk")
	private Filial fil_codigo = new Filial();

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "ent_codigo.ent_nomefantasia", principal = 1)
	@Basic
	@ManyToOne
	@JoinColumn(name = "ent_codigo", nullable = false)
	@ForeignKey(name = "ent_codigo_fk")
	private Entidade ent_codigo = new Entidade();

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "com_descricao", principal = 3)
	@Basic
	@Column(nullable = true, length = 100)
	private String com_descricao;

	@Audited
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "val_comissao", fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
	private List<ValorComissaoFuncionarioFilial> valorComissaoFuncionarioFilials = new ArrayList<ValorComissaoFuncionarioFilial>();

	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}
	
	public void setValorComissaoFuncionarioFilials(
			List<ValorComissaoFuncionarioFilial> valorComissaoFuncionarioFilials) { 
		this.valorComissaoFuncionarioFilials = valorComissaoFuncionarioFilials;
	}

	public List<ValorComissaoFuncionarioFilial> getValorComissaoFuncionarioFilials() {
		return valorComissaoFuncionarioFilials;
	}

	public void addValorComissao(ValorComissaoFuncionarioFilial comissao) {
		valorComissaoFuncionarioFilials.add(comissao);
	}

	public void removeValorComissao(ValorComissaoFuncionarioFilial comissao) {
		valorComissaoFuncionarioFilials.remove(comissao);
	}

	public void setCom_descricao(String com_descricao) {
		this.com_descricao = com_descricao;
	}

	public String getCom_descricao() {
		return com_descricao;
	}

	public Long getCom_codigo() {
		return com_codigo;
	}

	public void setCom_codigo(Long com_codigo) {
		this.com_codigo = com_codigo;
	}

	public Filial getFil_codigo() {
		return fil_codigo;
	}

	public void setFil_codigo(Filial fil_codigo) {
		this.fil_codigo = fil_codigo;
	}

	public void setEnt_codigo(Entidade ent_codigo) throws Exception {
		if (ent_codigo != null) {
			if (!ent_codigo.getEnt_tipo().equals(
					TipoCadastro.TIPO_CADASTRO_FUNCIONARIO)) {
				throw new Exception(
						"Tipo da entidade adicionada não é de funcionário.");
			}
		}
		this.ent_codigo = ent_codigo;
	}

	public Entidade getEnt_codigo() {
		return ent_codigo;
	}

	public JSONObject getJson() {
		return new JSONObject(this);
	}
	
	
	@Transient
	public boolean possuiPadrao() {
		for (ValorComissaoFuncionarioFilial objeto : this.getValorComissaoFuncionarioFilials()){
			if (objeto.getVal_tipo().equalsIgnoreCase("PADRAO")){
				
				return false;
			}
		}
		return true;
	}

}
