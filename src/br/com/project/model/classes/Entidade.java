package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.acessos.Permissao;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.enums.TipoCadastro;
import br.com.project.enums.TipoPessoa;

@SuppressWarnings("deprecation")
@Audited
@Entity
@Table(name = "entidade")
@SequenceGenerator(name = "entidade_seq", sequenceName = "entidade_seq", initialValue = 1, allocationSize = 1)
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "ent_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidade_seq")
	private Long ent_codigo;

	@Enumerated(EnumType.STRING)
	private TipoCadastro ent_tipo;

	@Enumerated(EnumType.STRING)
	private TipoPessoa ent_tipopessoa;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome Fantasia", campoConsulta = "ent_nomefantasia", principal = 1)
	@Column(length = 100)
	private String ent_nomefantasia;

	@IdentificaCampoPesquisa(descricaoCampo = "Razão Social", campoConsulta = "ent_razao", principal = 2)
	@Column(length = 100)
	private String ent_razao;

	@Column(length = 100)
	private String ent_endereco;

	@Column(length = 50)
	private String ent_complemento;

	@Column(length = 9)
	private String ent_cep;

	@Column(length = 15)
	private String ent_fone;

	@Column(length = 15)
	private String ent_fax;

	@Column(length = 15)
	private String ent_celular;

	@Column(length = 80)
	private String ent_contato;

	@Column(length = 100)
	private String ent_emailcontato;

	@Column(length = 100)
	private String ent_email;

	@Column(length = 100)
	private String ent_emailmovimento;

	@IdentificaCampoPesquisa(descricaoCampo = "Cidade", campoConsulta = "cid_codigo.cid_descricao")
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	@ForeignKey(name = "cid_codigo_fk")
	@JoinColumn(name = "cid_codigo")
	private Cidade cid_codigo = new Cidade();

	@IdentificaCampoPesquisa(descricaoCampo = "Bairro", campoConsulta = "bai_codigo.bai_descricao")
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	@ForeignKey(name = "bai_codigo_fk")
	@JoinColumn(name = "bai_codigo")
	private Bairro bai_codigo = new Bairro();

	@Column(length = 250)
	private String ent_observacao;

	@Column(length = 20, nullable = true, unique = true)
	@Index(name = "xlogin")
	private String ent_login = null;

	@Column(length = 20, nullable = true)
	private String ent_senha;

	@Column(nullable = true)
	private Boolean ent_mudarsenha;

	@Column(nullable = false)
	private Boolean ent_inativo = false;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_datacadastro = new Date();

	@Temporal(TemporalType.DATE)
	private Date ent_datanascimento;

	@IdentificaCampoPesquisa(descricaoCampo = "Filial", campoConsulta = "fil_codigo.fil_descricao")
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	@ForeignKey(name = "fil_codigo_fk")
	@JoinColumn(name = "fil_codigo")
	private Filial fil_codigo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_ultimoacesso;

	@CollectionOfElements
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JoinTable(name = "entidadeacesso", uniqueConstraints = { @UniqueConstraint(name = "unique_acesso_entidade_key", columnNames = {
			"ent_codigo", "esa_codigo" }) }, joinColumns = { @JoinColumn(name = "ent_codigo") })
	@Column(name = "esa_codigo", length = 20)
	private Set<String> acessos = new HashSet<String>();
	

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
		map.put("ent_codigo", ent_codigo);
		map.put("ent_login", ent_login);
		map.put("ent_nomefantasia", ent_nomefantasia);
		return new JSONObject(map);
	}

	public Long getEnt_codigo() {
		return ent_codigo;
	}

	public void setEnt_codigo(Long ent_codigo) {
		this.ent_codigo = ent_codigo;
	}

	public TipoCadastro getEnt_tipo() {
		return ent_tipo;
	}

	public void setEnt_tipo(TipoCadastro ent_tipo) {
		this.ent_tipo = ent_tipo;
	}

	public TipoPessoa getEnt_tipopessoa() {
		return ent_tipopessoa;
	}

	public void setEnt_tipopessoa(TipoPessoa ent_tipopessoa) {
		this.ent_tipopessoa = ent_tipopessoa;
	}

	public String getEnt_nomefantasia() {
		return ent_nomefantasia;
	}

	public void setEnt_nomefantasia(String ent_nomefantasia) {
		this.ent_nomefantasia = ent_nomefantasia;
	}

	public String getEnt_razao() {
		return ent_razao;
	}

	public void setEnt_razao(String ent_razao) {
		this.ent_razao = ent_razao;
	}

	public String getEnt_endereco() {
		return ent_endereco;
	}

	public void setEnt_endereco(String ent_endereco) {
		this.ent_endereco = ent_endereco;
	}

	public String getEnt_complemento() {
		return ent_complemento;
	}

	public void setEnt_complemento(String ent_complemento) {
		this.ent_complemento = ent_complemento;
	}

	public String getEnt_cep() {
		return ent_cep;
	}

	public void setEnt_cep(String ent_cep) {
		this.ent_cep = ent_cep;
	}

	public String getEnt_fone() {
		return ent_fone;
	}

	public void setEnt_fone(String ent_fone) {
		this.ent_fone = ent_fone;
	}

	public String getEnt_fax() {
		return ent_fax;
	}

	public void setEnt_fax(String ent_fax) {
		this.ent_fax = ent_fax;
	}

	public String getEnt_celular() {
		return ent_celular;
	}

	public void setEnt_celular(String ent_celular) {
		this.ent_celular = ent_celular;
	}

	public String getEnt_contato() {
		return ent_contato;
	}

	public void setEnt_contato(String ent_contato) {
		this.ent_contato = ent_contato;
	}

	public String getEnt_emailcontato() {
		return ent_emailcontato;
	}

	public void setEnt_emailcontato(String ent_emailcontato) {
		this.ent_emailcontato = ent_emailcontato;
	}

	public String getEnt_email() {
		return ent_email;
	}

	public void setEnt_email(String ent_email) {
		this.ent_email = ent_email;
	}

	public String getEnt_emailmovimento() {
		return ent_emailmovimento;
	}

	public void setEnt_emailmovimento(String ent_emailmovimento) {
		this.ent_emailmovimento = ent_emailmovimento;
	}

	public Cidade getCid_codigo() {
		return cid_codigo;
	}

	public void setCid_codigo(Cidade cid_codigo) {
		this.cid_codigo = cid_codigo;
	}

	public Bairro getBai_codigo() {
		return bai_codigo;
	}

	public void setBai_codigo(Bairro bai_codigo) {
		this.bai_codigo = bai_codigo;
	}

	public String getEnt_observacao() {
		return ent_observacao;
	}

	public void setEnt_observacao(String ent_observacao) {
		this.ent_observacao = ent_observacao;
	}

	public String getEnt_login() {
		return ent_login;
	}

	public void setEnt_login(String ent_login) {
		this.ent_login = ent_login;
	}

	public String getEnt_senha() {
		return ent_senha;
	}

	public void setEnt_senha(String ent_senha) {
		this.ent_senha = ent_senha;
	}

	public Boolean getEnt_mudarsenha() {
		return ent_mudarsenha;
	}

	public void setEnt_mudarsenha(Boolean ent_mudarsenha) {
		this.ent_mudarsenha = ent_mudarsenha;
	}

	public Boolean getEnt_inativo() {
		return ent_inativo;
	}

	public void setEnt_inativo(Boolean ent_inativo) {
		this.ent_inativo = ent_inativo;
	}

	public Date getEnt_datacadastro() {
		return ent_datacadastro;
	}

	public void setEnt_datacadastro(Date ent_datacadastro) {
		this.ent_datacadastro = ent_datacadastro;
	}

	public Date getEnt_datanascimento() {
		return ent_datanascimento;
	}

	public void setEnt_datanascimento(Date ent_datanascimento) {
		this.ent_datanascimento = ent_datanascimento;
	}

	public Filial getFil_codigo() {
		return fil_codigo;
	}

	public void setFil_codigo(Filial fil_codigo) {
		this.fil_codigo = fil_codigo;
	}

	public Date getEnt_ultimoacesso() {
		return ent_ultimoacesso;
	}

	public void setEnt_ultimoacesso(Date ent_ultimoacesso) {
		this.ent_ultimoacesso = ent_ultimoacesso;
	}

	public Set<String> getAcessos() {
		return acessos;
	}
	

	public List<String> getAcessosOrdenadas() {
		List<String> retorno = new ArrayList<String>();
		for (String acesso : acessos) {
			retorno.add(acesso);
		}
		Collections.sort(retorno);
		return retorno;
	}

	public Set<Permissao> getAcessosPermissao() {
		Set<Permissao> permissoes = new HashSet<Permissao>();
		for (String acesso : getAcessosOrdenadas()) {
			for (Permissao acess : Permissao.values()) {
				if (acesso.equalsIgnoreCase(acess.name())) {
					permissoes.add(acess);
				}
			}
		}
		return permissoes;
	}

	public void setAcessos(Set<String> acessos) {
		this.acessos = acessos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ent_codigo == null) ? 0 : ent_codigo.hashCode());
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
		Entidade other = (Entidade) obj;
		if (ent_codigo == null) {
			if (other.ent_codigo != null)
				return false;
		} else if (!ent_codigo.equals(other.ent_codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entidade [ent_codigo=" + ent_codigo + ", ent_nomefantasia="
				+ ent_nomefantasia + ", ent_razao=" + ent_razao + "]";
	}

}
