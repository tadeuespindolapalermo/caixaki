package br.com.project.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;

@Controller
@Scope("session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<Cidade> list = new CarregamentoLazyListForObject<Cidade>();
	private Cidade objetoSelecionado = new Cidade();
	private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_cidade.jsf?faces-redirect=true";

	@Autowired
	private CidadeController cidadeController;

	public void setCidadeController(CidadeController cidadeController) {
		this.cidadeController = cidadeController;
	}

	public CidadeController getCidadeController() {
		return cidadeController;
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_cidade");
		super.setNomeRelatorioSaida("report_cidade");
		List<?> list = cidadeController.finListOrderByProperty(Cidade.class, "estado.est_nome");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	@Override
	protected Class<?> getClassImplement() {
		return Cidade.class;
	}

	public void setObjetoSelecionado(Cidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	public Cidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public CarregamentoLazyListForObject<Cidade> getList() {
		return list;
	}

	@Override
	@RequestMapping(value = { "**/find_cidade" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Cidade();
	}

	@Override
	public void saveNotReturn() throws Exception { 
			if (validarCampoObrigatorio(objetoSelecionado)) {
				list.clear();
				objetoSelecionado = cidadeController.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				objetoSelecionado = new Cidade();
				sucesso();
			}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public void excluir() throws Exception {
			if (objetoSelecionado.getCid_codigo() != null
					&& objetoSelecionado.getCid_codigo() > 0) {
				cidadeController.delete(objetoSelecionado);
				list.remove(objetoSelecionado);
				objetoSelecionado = new Cidade();
				sucesso();
			}
	}

	@Override
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Cidade();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	/**
	 * Ivocado pelo botão consultar
	 */
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		return url;
	}

	@RequestMapping("**/findCidade")
	public void findCidade(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codCidade") Long codCidade)
			throws Exception {

			Cidade cidade = cidadeController.findUninqueByPropertyId(
					Cidade.class, codCidade, "cid_codigo");
			if (cidade != null) {
				httpServletResponse.getWriter().write(
						cidade.getJson().toString());
			}

	}

	@Override
	protected InterfaceCrud<Cidade> getController() {
		return cidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}

}
