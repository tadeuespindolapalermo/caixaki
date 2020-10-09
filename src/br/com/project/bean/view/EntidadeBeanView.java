package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.bean.geral.EntidadeAtualizaSenhaBean;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.TipoCadastro;
import br.com.project.enums.TipoPessoa;
import br.com.project.geral.controller.BairroController;
import br.com.project.geral.controller.CidadeController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.EntidadeEntidadeController;
import br.com.project.geral.controller.FilialController;
import br.com.project.model.classes.Bairro;
import br.com.project.model.classes.Cidade;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.EntidadeEntidade;
import br.com.project.model.classes.Filial;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();
	private Entidade objetoSelecionado = new Entidade();
	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	private String associacaoText = "";
	private String associacaoTextDescricao = "";

	private String url = "/cadastro/cad_entidade.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_entidade.jsf?faces-redirect=true";

	private String url_cliente = "/cadastro/cad_cliente.jsf?faces-redirect=true";
	private String url_find_cliente = "/cadastro/find_cliente.jsf?faces-redirect=true";

	private String url_fornecedor = "/cadastro/cad_fornecedor.jsf?faces-redirect=true";
	private String url_find_fornecedor = "/cadastro/find_fornecedor.jsf?faces-redirect=true";

	private String url_vendedor = "/cadastro/cad_vendedor.jsf?faces-redirect=true";
	private String url_find_vendedor = "/cadastro/find_vendedor.jsf?faces-redirect=true";

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;

	@Autowired
	private CidadeController cidadeController;

	@Autowired
	private BairroController bairroController;

	@Autowired
	private FilialController filialController;

	@Autowired
	private EntidadeEntidadeController entidadeEntidadeController;

	public String getUsuarioLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}

	public Date getUltimoAcesso() throws Exception {
		return contextoBean.getEntidadeLogada().getEnt_ultimoacesso();
	}

	@Override
	public StreamedContent getArquivoReport() throws Exception {

		String nomeReport = null;

		TipoCadastro tipoCadastro = getTipoEntidadeTemp();

		if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_CONSTRUTORA))
			nomeReport = "report_construtora";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_CLIENTE))
			nomeReport = "report_cliente";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_FORNECEDOR))
			nomeReport = "report_fornecedor";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_VENDEDOR))
			nomeReport = "report_vendedor";

		super.setNomeRelatorioJasper(nomeReport);
		super.setNomeRelatorioSaida(nomeReport);
		List<?> list = entidadeController.findListByProperty(Entidade.class,
				"ent_tipo", tipoCadastro.name());
		super.setListDataBeanColletionReport(list);

		return super.getArquivoReport();
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		if (getTipoEntidadeTemp()
				.equals(TipoCadastro.TIPO_CADASTRO_CONSTRUTORA))
			return url;
		else if (getTipoEntidadeTemp().equals(
				TipoCadastro.TIPO_CADASTRO_CLIENTE))
			return url_cliente;
		else if (getTipoEntidadeTemp().equals(
				TipoCadastro.TIPO_CADASTRO_FORNECEDOR))
			return url_fornecedor;
		else if (getTipoEntidadeTemp().equals(
				TipoCadastro.TIPO_CADASTRO_VENDEDOR))
			return url_vendedor;
		else
			return null;
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		if (objetoSelecionado.getEnt_tipo().equals(
				TipoCadastro.TIPO_CADASTRO_CLIENTE))
			return url_cliente;
		else if (objetoSelecionado.getEnt_tipo().equals(
				TipoCadastro.TIPO_CADASTRO_FORNECEDOR))
			return url_fornecedor;
		else if (objetoSelecionado.getEnt_tipo().equals(
				TipoCadastro.TIPO_CADASTRO_VENDEDOR)) {
			EntidadeEntidade entidadeEntidade = (entidadeEntidadeController
					.findUninqueByProperty(EntidadeEntidade.class,
							objetoSelecionado.getEnt_codigo(),
							"ent_codvendedor"));
			if (entidadeEntidade != null) {
				associacaoText = entidadeEntidade.getEnt_codconstrutora()
						.getEnt_codigo().toString();
				associacaoTextDescricao = entidadeEntidade.getEnt_codconstrutora().getEnt_nomefantasia();
			}
			return url_vendedor;
		}
		return url;
	}

	@Override
	public void saveNotReturn() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			objetoSelecionado.setEnt_tipo(getTipoEntidadeTemp());
			list.clear();
			
			if(!getTipoEntidadeTemp().equals(TipoCadastro.TIPO_CADASTRO_VENDEDOR))
				objetoSelecionado = entidadeController.merge(objetoSelecionado);

			if (getTipoEntidadeTemp().equals(
					TipoCadastro.TIPO_CADASTRO_VENDEDOR)
					&& associacaoText != null && !associacaoText.isEmpty()) {
				
				Entidade contr = entidadeController.findUninqueByPropertyId(
						Entidade.class, Long.valueOf(associacaoText), "ent_codigo",
						" and entity.ent_inativo is false and ent_tipo = '" + TipoCadastro.TIPO_CADASTRO_CONSTRUTORA.name() + "'");
				if (contr == null){
					addMsg("Construtora informada inválida");
					return ;
				}
				objetoSelecionado = entidadeController.merge(objetoSelecionado);
				entidadeEntidadeController.removeAssociacao(objetoSelecionado.getEnt_codigo());
				EntidadeEntidade associacao = new EntidadeEntidade();
				Entidade construtora = new Entidade();
				construtora.setEnt_codigo(Long.valueOf(associacaoText));
				associacao.setEnt_codvendedor(objetoSelecionado);
				associacao.setEnt_codconstrutora(construtora);
				associacao = entidadeEntidadeController.merge(associacao); 
			} else if (getTipoEntidadeTemp().equals(
					TipoCadastro.TIPO_CADASTRO_VENDEDOR)) {
				entidadeEntidadeController.removeAssociacao(objetoSelecionado
						.getEnt_codigo());
			}

			list.add(objetoSelecionado);
			objetoSelecionado = new Entidade();
			objetoSelecionado.setEnt_tipo(getTipoEntidadeTemp());
			associacaoText = "";
			associacaoTextDescricao = "";
			sucesso();
		}
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	@RequestMapping({ "**/find_entidade", "**/find_cliente",
			"**/find_fornecedor", "**/find_vendedor" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Entidade();
		objetoSelecionado.setEnt_tipo(getTipoEntidadeTemp());
		entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
		associacaoTextDescricao = "";
		associacaoText = "";
	}

	@Override
	protected Class<?> getClassImplement() {
		return Entidade.class;
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getEnt_codigo() != null
				&& objetoSelecionado.getEnt_codigo() > 0) {
			entidadeEntidadeController.removeAssociacao(objetoSelecionado.getEnt_codigo()); 
			entidadeController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Entidade();
			objetoSelecionado.setEnt_tipo(getTipoEntidadeTemp());
			sucesso();
		}
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		if (getTipoEntidadeTemp()
				.equals(TipoCadastro.TIPO_CADASTRO_CONSTRUTORA))
			return urlFind;
		else if (getTipoEntidadeTemp().equals(
				TipoCadastro.TIPO_CADASTRO_CLIENTE))
			return url_find_cliente;
		else if (getTipoEntidadeTemp().equals(
				TipoCadastro.TIPO_CADASTRO_FORNECEDOR))
			return url_find_fornecedor;
		else if (getTipoEntidadeTemp().equals(
				TipoCadastro.TIPO_CADASTRO_VENDEDOR))
			return url_find_vendedor;
		else
			return null;
	}

	@RequestMapping("**/findEntidade")
	public void findEntidade(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codEntidade") Long codEntidade)
			throws Exception {
		Entidade entidade = entidadeController.findUninqueByPropertyId(
				Entidade.class, codEntidade, "ent_codigo",
				condicaoAndParaPesquisa());
		if (entidade != null) {
			httpServletResponse.getWriter()
					.write(entidade.getJson().toString());
		}

	}

	@RequestMapping("**/findUserDestino")
	public void findUserDestino(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codEntidade") Long codEntidade)
			throws Exception {
		Entidade entidade = entidadeController.findFuncionario(codEntidade);
		if (entidade != null) {
			httpServletResponse.getWriter()
					.write(entidade.getJson().toString());
		}

	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Entidade();
		objetoSelecionado.setEnt_tipo(getTipoEntidadeTemp());
		list.clear();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(),
				super.getSqlLazyQuery());
	}

	public List<SelectItem> getListTipoPessoa() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoPessoa tipoPessoa : TipoPessoa.getValuePadraoJuridica()) {
			items.add(new SelectItem(tipoPessoa.name(), tipoPessoa.toString()));
		}
		return items;
	}

	@RequestMapping("**/addBairroEntidade")
	public void addBairroFilial(@RequestParam Long codBairro) throws Exception {
		if (codBairro != null && codBairro > 0) {
			objetoSelecionado.setBai_codigo(bairroController
					.findUninqueByProperty(Bairro.class, codBairro,
							"bai_codigo"));
		}
	}

	@RequestMapping("**/addCidadeEntidade")
	public void addCidadeFilial(@RequestParam Long codCidade) throws Exception {

		if (codCidade != null && codCidade > 0) {
			objetoSelecionado.setCid_codigo(cidadeController
					.findUninqueByProperty(Cidade.class, codCidade,
							"cid_codigo"));
		}
	}

	@RequestMapping("**/addFilialEntidade")
	public void addFilialEntidade(@RequestParam Long codFilial)
			throws Exception {

		if (codFilial != null && codFilial > 0) {
			objetoSelecionado.setFil_codigo(filialController
					.findUninqueByProperty(Filial.class, codFilial,
							"fil_codigo"));
		}
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}

	public CarregamentoLazyListForObject<Entidade> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Entidade> list) {
		this.list = list;
	}

	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "and entity.ent_tipo = '" + getTipoEntidadeTemp().name() + "' "
				+ consultarInativos();
	}

	public void updateSenha() throws Exception {
		Entidade entidadeLogada = contextoBean.getEntidadeLogada();
		if (!entidadeAtualizaSenhaBean.getSenhaAtual().equals(
				entidadeLogada.getEnt_senha())) {
			addMsg("A senha atual não é válida");
			return;
		} else if (entidadeAtualizaSenhaBean.getSenhaAtual().equals(
				entidadeAtualizaSenhaBean.getNovaSenha())) {
			addMsg("A senha atual não pode ser igual a nova senha.");
			return;
		} else if (!entidadeAtualizaSenhaBean.getNovaSenha().equals(
				entidadeAtualizaSenhaBean.getConfirmaSenha())) {
			addMsg("A nova senha e a confimação não conferem.");
			return;
		} else {
			entidadeLogada.setEnt_senha(entidadeAtualizaSenhaBean
					.getNovaSenha());
			entidadeController.saveOrUpdate(entidadeLogada);
			entidadeLogada = entidadeController.findById(Entidade.class,
					entidadeLogada.getEnt_codigo());
			if (entidadeLogada.getEnt_senha().equals(
					entidadeAtualizaSenhaBean.getNovaSenha())) {
				sucesso();
			} else {
				addMsg("A nova senha não pode ser atualizada.");
				error();
			}
		}

		entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	}

	public void setEntidadeAtualizaSenhaBean(
			EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean) {
		this.entidadeAtualizaSenhaBean = entidadeAtualizaSenhaBean;
	}

	public EntidadeAtualizaSenhaBean getEntidadeAtualizaSenhaBean() {
		return entidadeAtualizaSenhaBean;
	}

	@RequestMapping("**/findConstrutora2")
	public void findConstrutora2(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codConstrutora") Long codConstrutora)
			throws Exception {

		Entidade contr = entidadeController.findUninqueByPropertyId(
				Entidade.class, codConstrutora, "ent_codigo",
				" and entity.ent_inativo is false and ent_tipo = '" + TipoCadastro.TIPO_CADASTRO_CONSTRUTORA.name() + "'");
		if (contr != null) {
			httpServletResponse.getWriter().write(contr.getJson().toString());
		}

	}


	public void setAssociacaoText(String associacaoText) {
		this.associacaoText = associacaoText;
	}

	public String getAssociacaoText() {
		return associacaoText;
	}
	
	public void setAssociacaoTextDescricao(String associacaoTextDescricao) {
		this.associacaoTextDescricao = associacaoTextDescricao;
	}
	
	public String getAssociacaoTextDescricao() {
		return associacaoTextDescricao;
	}
}
