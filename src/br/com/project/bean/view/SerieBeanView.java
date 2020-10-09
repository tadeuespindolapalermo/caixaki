package br.com.project.bean.view;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.SerieController;
import br.com.project.model.classes.Serie;

@Controller
@Scope(value = "session")
@ManagedBean(name = "serieBeanView")
public class SerieBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<Serie> list = new CarregamentoLazyListForObject<Serie>();
	private Serie objetoSelecionado = new Serie();
	private String url = "/cadastro/cad_serie.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_serie.jsf?faces-redirect=true";

	@Resource
	private SerieController serieController;

	public void setSerieController(SerieController serieController) {
		this.serieController = serieController;
	}

	public SerieController getSerieController() {
		return serieController;
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_serie");
		super.setNomeRelatorioSaida("report_serie");
		List<?> list = serieController.finListOrderByProperty(Serie.class, "ser_codigo");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	@Override
	protected Class<?> getClassImplement() {
		return Serie.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return serieController;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@Override
	@RequestMapping({ "**/find_serie" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Serie();
	}

	@Override
	public void saveNotReturn() throws Exception {
			if (validarCampoObrigatorio(objetoSelecionado)) {
				list.clear();
				objetoSelecionado = serieController.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				objetoSelecionado = new Serie();
				sucesso();
			}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}


	@Override
	public void excluir() throws Exception {
			if (objetoSelecionado.getSer_codigo() != null
					&& objetoSelecionado.getSer_codigo() > 0) {
				serieController.delete(objetoSelecionado);
				list.remove(objetoSelecionado);
				objetoSelecionado = new Serie();
				sucesso();
			}
	}

	@Override
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Serie();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
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

	public CarregamentoLazyListForObject<Serie> getList() {
		return list;
	}
	

	public void setList(CarregamentoLazyListForObject<Serie> list) {
		this.list = list;
	}

	public Serie getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Serie objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}

}
