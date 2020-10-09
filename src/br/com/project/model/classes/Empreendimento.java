package br.com.project.model.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "empreendimento")
@SequenceGenerator(name = "empreendimento_seq", sequenceName = "empreendimento_seq", initialValue = 1, allocationSize = 1)
public class Empreendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "emp_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empreendimento_seq")
	private Long emp_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "emp_descricao", principal = 1)
	@Column(length = 100, nullable = false)
	private String emp_descricao;

	@IdentificaCampoPesquisa(descricaoCampo = "Contrutora", campoConsulta = "ent_codigo.ent_nomefantasia")
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "ent_codigo_fk")
	@JoinColumn(name = "ent_codigo", nullable = false)
	private Entidade ent_codigo = new Entidade();
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("emp_id", emp_id);
		map.put("emp_descricao", emp_descricao);
		return new JSONObject(map);
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_descricao() {
		return emp_descricao;
	}

	public void setEmp_descricao(String emp_descricao) {
		this.emp_descricao = emp_descricao;
	}

	public Entidade getEnt_codigo() {
		return ent_codigo;
	}

	public void setEnt_codigo(Entidade ent_codigo) {
		this.ent_codigo = ent_codigo;
	}

}