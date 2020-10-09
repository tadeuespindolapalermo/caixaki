package br.com.project.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.EntidadeEntidadeController;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.EntidadeEntidade;
import br.com.project.util.all.Messagens;

@Controller
@ManagedBean(name = "entidadeEntidadeBeanView")
@Scope("session")
public class EntidadeEntidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<EntidadeEntidade> list = new CarregamentoLazyListForObject<EntidadeEntidade>();
	private EntidadeEntidade objetoSelecionado = new EntidadeEntidade();
	private String url = "/cadastro/cad_associar_vendedor.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_associar_vendedor.jsf?faces-redirect=true";

	@Autowired
	private EntidadeEntidadeController entidadeEntidadeController;

	@Autowired
	private EntidadeController entidadeController;

	public void setEntidadeEntidadeController(
			EntidadeEntidadeController entidadeEntidadeController) {
		this.entidadeEntidadeController = entidadeEntidadeController;
	}

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_entidade_entidade");
		super.setNomeRelatorioSaida("report_entidade_entidade");
		List<?> list = entidadeEntidadeController.finListOrderByProperty(
				EntidadeEntidade.class, "ent_codconstrutora.ent_nomefantasia");
		super.setListDataBeanColletionReport(list);
		return super.getArquivoReport();
	}

	@Override
	protected Class<EntidadeEntidade> getClassImplement() {
		return EntidadeEntidade.class;
	}

	@RequestMapping("**/addConstrutoraVendedor")
	public void addConstrutoraVendedor(@RequestParam Long codConstrutora)
			throws Exception {
		if (codConstrutora != null && codConstrutora > 0) {
			Entidade entidade = entidadeController
					.findContrutora(codConstrutora);

			objetoSelecionado.setEnt_codconstrutora(entidade != null ? entidade
					: new Entidade());
		}
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@RequestMapping("**/addVendedorContrutora")
	public void addVendedorContrutora(@RequestParam Long codVendedor)
			throws Exception {
		if (codVendedor != null && codVendedor > 0) {
			Entidade entidade = entidadeController.findVendedor(codVendedor);
			objetoSelecionado.setEnt_codvendedor(entidade != null ? entidade
					: new Entidade());
		}
	}

	@RequestMapping("**/findVendedor")
	public void findVendedor(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codigoVendedor") Long codigoVendedor)
			throws Exception {
		Entidade entidade = entidadeController.findVendedor(codigoVendedor);
		if (entidade != null) {
			httpServletResponse.getWriter()
					.write(entidade.getJson().toString());
		}
	}

	@Override
	@RequestMapping({ "**/find_associar_vendedor" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new EntidadeEntidade();
	}

	@Override
	public void saveNotReturn() throws Exception {
			if (validarCampoObrigatorio(objetoSelecionado)) {
			if (!entidadeEntidadeController.possuiAssociacao(objetoSelecionado)){
				gravaAssociacao();
				} else {
					Messagens.msgSeverityInfor("Associação já existe.");
				}
			}
	}

	private void gravaAssociacao() throws Exception {
		list.clear();
		objetoSelecionado = entidadeEntidadeController
				.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new EntidadeEntidade();
		sucesso();
	}

	@Override
	public void saveEdit() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
				gravaAssociacao();
			}
		
	}
	
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getEnts_codigo() != null
				&& objetoSelecionado.getEnts_codigo() > 0) {
			entidadeEntidadeController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new EntidadeEntidade();
			sucesso();
		}
	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new EntidadeEntidade();
		list.clear();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(),
				super.getSqlLazyQuery());
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		return url;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return entidadeEntidadeController;
	}

	public CarregamentoLazyListForObject<EntidadeEntidade> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<EntidadeEntidade> list) {
		this.list = list;
	}

	public EntidadeEntidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(EntidadeEntidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return consultarInativosBoolean() ? "" : " and entity.ent_codconstrutora.ent_inativo is false and entity.ent_codvendedor.ent_inativo is false";
	}

}
