package br.com.project.bean.view;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.BairroController;
import br.com.project.model.classes.Bairro;

@Controller
@Scope(value = "session")
@ManagedBean(name = "bairroBeanView")
public class BairroBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<Bairro> list = new CarregamentoLazyListForObject<Bairro>();
	private Bairro objetoSelecionado = new Bairro();
	private String url = "/cadastro/cad_bairro.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_bairro.jsf?faces-redirect=true";

	@Resource
	private BairroController bairroController;

	public void setBairroController(BairroController bairroController) {
		this.bairroController = bairroController;
	}

	public BairroController getBairroController() {
		return bairroController;
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_bairro");
		super.setNomeRelatorioSaida("report_bairro");
		List<?> list = bairroController.finListOrderByProperty(Bairro.class, "bai_codigo");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	/**
	 * Ivocado pelo botão novo
	 */
	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	/**
	 * Ivocado pelo botão salvar
	 */
	@Override
	public void saveNotReturn() throws Exception {
			if (validarCampoObrigatorio(objetoSelecionado)) {
				list.clear();
				objetoSelecionado = bairroController.merge(objetoSelecionado);
				list.getList().add(objetoSelecionado);
				objetoSelecionado = new Bairro();
				sucesso();
			}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}
	
	/**
	 * Ivocado pelo botão exlcluir
	 */
	@Override
	public void excluir() throws Exception {
			if (objetoSelecionado.getBai_codigo() != null
					&& objetoSelecionado.getBai_codigo() > 0) {
				bairroController.delete(objetoSelecionado);
				list.getList().remove(objetoSelecionado);
				objetoSelecionado = new Bairro();
				sucesso();
			}
	}

	/**
	 * Ivocado pelo botão consultar
	 */
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Bairro();
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

	@Override
	@RequestMapping({ "**/find_bairro" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Bairro();
	}

	@Override
	protected Class<?> getClassImplement() {
		return Bairro.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return bairroController;
	}

	public CarregamentoLazyListForObject<Bairro> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Bairro> list) {
		this.list = list;
	}

	public Bairro getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Bairro objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@RequestMapping("**/findBairro")
	public void findBairro(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codBairro") Long codBairro)
			throws Exception {
			Bairro bairro = bairroController.findUninqueByPropertyId(
					Bairro.class, codBairro, "bai_codigo");
			if (bairro != null) {
				httpServletResponse.getWriter().write(
						bairro.getJson().toString());
			}

	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}
	
	public Object onRowSelect(SelectEvent event) {
		objetoSelecionado = (Bairro) super.onRowSelect(event);
		return null;
	}
	
	public Object onRowUnselect(UnselectEvent event) {
		objetoSelecionado = (Bairro) super.onRowUnselect(event);
		return null;
	}
	
	
}
