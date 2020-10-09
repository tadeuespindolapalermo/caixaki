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
@Table(name = "entidade_entidade")
@SequenceGenerator(name = "entidade_entidade_seq", sequenceName = "entidade_entidade_seq", initialValue = 1, allocationSize = 1)
public class EntidadeEntidade implements Serializable {

	private static final long serialVersionUID = -5109692706237143193L;

	@Id
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "ents_codigo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidade_entidade_seq")
	private Long ents_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Construtora", campoConsulta = "ent_codconstrutora.ent_nomefantasia", principal = 1)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "ent_codconstrutora")
	@ForeignKey(name = "ent_codconstrutora_fk")
	private Entidade ent_codconstrutora = new Entidade();

	@IdentificaCampoPesquisa(descricaoCampo = "Vendedor", campoConsulta = "ent_codvendedor.ent_nomefantasia", principal = 2)
	@JoinColumn(nullable = false, name = "ent_codvendedor")
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "ent_codvendedor_fk")
	private Entidade ent_codvendedor = new Entidade();
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Entidade getEnt_codconstrutora() {
		return ent_codconstrutora;
	}

	public void setEnt_codconstrutora(Entidade ent_codconstrutora) {
		this.ent_codconstrutora = ent_codconstrutora;
	}

	public Entidade getEnt_codvendedor() {
		return ent_codvendedor;
	}

	public void setEnt_codvendedor(Entidade ent_codvendedor) {
		this.ent_codvendedor = ent_codvendedor;
	}

	public Long getEnts_codigo() {
		return ents_codigo;
	}

	public void setEnts_codigo(Long ents_codigo) {
		this.ents_codigo = ents_codigo;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ents_codigo", ents_codigo);
		map.put("ent_codconstrutora", ent_codconstrutora.getEnt_codigo());
		map.put("ent_codvendedor", ent_codvendedor.getEnt_codigo());
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((ent_codconstrutora == null) ? 0 : ent_codconstrutora
						.hashCode());
		result = prime * result
				+ ((ent_codvendedor == null) ? 0 : ent_codvendedor.hashCode());
		result = prime * result
				+ ((ents_codigo == null) ? 0 : ents_codigo.hashCode());
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
		EntidadeEntidade other = (EntidadeEntidade) obj;
		if (ent_codconstrutora == null) {
			if (other.ent_codconstrutora != null)
				return false;
		} else if (!ent_codconstrutora.equals(other.ent_codconstrutora))
			return false;
		if (ent_codvendedor == null) {
			if (other.ent_codvendedor != null)
				return false;
		} else if (!ent_codvendedor.equals(other.ent_codvendedor))
			return false;
		if (ents_codigo == null) {
			if (other.ents_codigo != null)
				return false;
		} else if (!ents_codigo.equals(other.ents_codigo))
			return false;
		return true;
	}

}
