package br.com.project.model.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import br.com.project.enums.EmailAutenticacao;
import br.com.project.enums.SegurancaEmail;

@Audited
@Entity
@Table(name = "filial")
@SequenceGenerator(name = "filial_seq", sequenceName = "filial_seq", initialValue = 1, allocationSize = 1)
public class Filial implements Serializable {

	private static final long serialVersionUID = 758145504502063775L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "fil_codigo")
	@Id
	@Column(name = "fil_codigo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filial_seq")
	private Long fil_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "fil_descricao", principal = 1)
	@Column(length = 100, nullable = false)
	private String fil_descricao;

	@Column(length = 100)
	private String fil_endereco;

	@Column(length = 20)
	private String fil_fone;

	@Column(length = 20)
	private String fil_fax;

	@Column(length = 15)
	private String fil_cep;

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "bai_codigo_fk")
	@JoinColumn(name = "bai_codigo")
	private Bairro bai_codigo = new Bairro();

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "cid_codigo_fk")
	@JoinColumn(name = "cid_codigo")
	private Cidade cid_codigo = new Cidade();

	@Column(length = 100)
	private String fil_enderecoemail;

	private Integer fil_portaemail;

	private Boolean fil_autenticaremail; 

	@Column(length = 100)
	@Enumerated(EnumType.STRING)
	private SegurancaEmail fil_segurancaemail;

	@Column(length = 100)
	@Enumerated(EnumType.STRING)
	private EmailAutenticacao fil_autenticacaoemail;

	@Column(length = 100)
	private String fil_usuarioemail;

	@Column(length = 100)
	private String fil_senhaemail;

	@Column(length = 50)
	private String fil_statuscomissao;
	
	private String fil_servidoremail;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public String getFil_statuscomissao() {
		return fil_statuscomissao;
	}

	public void setFil_statuscomissao(String fil_statuscomissao) {
		this.fil_statuscomissao = fil_statuscomissao;
	}

	public Long getFil_codigo() {
		return fil_codigo;
	}

	public void setFil_codigo(Long fil_codigo) {
		this.fil_codigo = fil_codigo;
	}

	public String getFil_descricao() {
		return fil_descricao;
	}

	public void setFil_descricao(String fil_descricao) {
		this.fil_descricao = fil_descricao;
	}

	public String getFil_endereco() {
		return fil_endereco;
	}

	public void setFil_endereco(String fil_endereco) {
		this.fil_endereco = fil_endereco;
	}

	public String getFil_fone() {
		return fil_fone;
	}

	public void setFil_fone(String fil_fone) {
		this.fil_fone = fil_fone;
	}

	public String getFil_fax() {
		return fil_fax;
	}

	public void setFil_fax(String fil_fax) {
		this.fil_fax = fil_fax;
	}

	public String getFil_cep() {
		return fil_cep;
	}

	public void setFil_cep(String fil_cep) {
		this.fil_cep = fil_cep;
	}

	public Bairro getBai_codigo() {
		return bai_codigo;
	}

	public void setBai_codigo(Bairro bai_codigo) {
		this.bai_codigo = bai_codigo;
	}

	public Cidade getCid_codigo() {
		return cid_codigo;
	}

	public void setCid_codigo(Cidade cid_codigo) {
		this.cid_codigo = cid_codigo;
	}

	public String getFil_enderecoemail() {
		return fil_enderecoemail;
	}

	public void setFil_enderecoemail(String fil_enderecoemail) {
		this.fil_enderecoemail = fil_enderecoemail;
	}

	public Integer getFil_portaemail() {
		return fil_portaemail;
	}

	public void setFil_portaemail(Integer fil_portaemail) {
		this.fil_portaemail = fil_portaemail;
	}

	public Boolean getFil_autenticaremail() {
		return fil_autenticaremail;
	}

	public void setFil_autenticaremail(Boolean fil_autenticaremail) {
		this.fil_autenticaremail = fil_autenticaremail;
	}

	public SegurancaEmail getFil_segurancaemail() {
		return fil_segurancaemail;
	}

	public void setFil_segurancaemail(SegurancaEmail fil_segurancaemail) {
		this.fil_segurancaemail = fil_segurancaemail;
	}

	public EmailAutenticacao getFil_autenticacaoemail() {
		return fil_autenticacaoemail;
	}

	public void setFil_autenticacaoemail(EmailAutenticacao fil_autenticacaoemail) {
		this.fil_autenticacaoemail = fil_autenticacaoemail;
	}

	public String getFil_usuarioemail() {
		return fil_usuarioemail;
	}

	public void setFil_usuarioemail(String fil_usuarioemail) {
		this.fil_usuarioemail = fil_usuarioemail;
	}

	public String getFil_senhaemail() {
		return fil_senhaemail;
	}

	public void setFil_senhaemail(String fil_senhaemail) {
		this.fil_senhaemail = fil_senhaemail;
	}
	

	public String getFil_servidoremail() {
		return fil_servidoremail;
	}
	
	public void setFil_servidoremail(String fil_servidoremail) {
		this.fil_servidoremail = fil_servidoremail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fil_codigo == null) ? 0 : fil_codigo.hashCode());
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
		Filial other = (Filial) obj;
		if (fil_codigo == null) {
			if (other.fil_codigo != null)
				return false;
		} else if (!fil_codigo.equals(other.fil_codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Filial [fil_codigo=" + fil_codigo + ", fil_descricao="
				+ fil_descricao + "]";
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("fil_codigo", fil_codigo);
		map.put("fil_descricao", fil_descricao);
		return new JSONObject(map);
	}
}
