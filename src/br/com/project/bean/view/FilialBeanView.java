package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.EmailAutenticacao;
import br.com.project.enums.SegurancaEmail;
import br.com.project.geral.controller.BairroController;
import br.com.project.geral.controller.CidadeController;
import br.com.project.geral.controller.FilialController;
import br.com.project.model.classes.Bairro;
import br.com.project.model.classes.Cidade;
import br.com.project.model.classes.Filial;

@Controller
@Scope(value = "session")
@ManagedBean(name = "filialBeanView")
public class FilialBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<Filial> list = new CarregamentoLazyListForObject<Filial>();
	private Filial objetoSelecionado = new Filial();
	private String url = "/cadastro/cad_filial.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_filial.jsf?faces-redirect=true";

	@Resource
	private FilialController filialController;

	@Resource
	private BairroController bairroController;

	@Resource
	private CidadeController cidadeController;

	public void setFilialController(FilialController filialController) {
		this.filialController = filialController;
	}

	public FilialController getFilialController() {
		return filialController;
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_filial");
		super.setNomeRelatorioSaida("report_filial");
		List<?> list = filialController.finListOrderByProperty(Filial.class, "fil_codigo");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	@Override
	protected Class<?> getClassImplement() {
		return Filial.class;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@Override
	public void saveNotReturn() throws Exception {
			if (objetoSelecionado.getBai_codigo() != null
					&& objetoSelecionado.getBai_codigo().getBai_codigo() != null
					&& objetoSelecionado.getBai_codigo().getBai_codigo() > 0) {
				objetoSelecionado
						.setBai_codigo(bairroController.findUninqueByProperty(
								Bairro.class, objetoSelecionado.getBai_codigo()
										.getBai_codigo(), "bai_codigo"));
			}

			if (objetoSelecionado.getCid_codigo() != null
					&& objetoSelecionado.getCid_codigo().getCid_codigo() != null
					&& objetoSelecionado.getCid_codigo().getCid_codigo() > 0) {
				objetoSelecionado
						.setCid_codigo(cidadeController.findUninqueByProperty(
								Cidade.class, objetoSelecionado.getCid_codigo()
										.getCid_codigo(), "cid_codigo"));
			}

			if (validarCampoObrigatorio(objetoSelecionado)) {
				list.clear();
				objetoSelecionado = filialController.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				objetoSelecionado = new Filial();
				sucesso();
			}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	public List<SelectItem> getListAutenticacaoEmail() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (EmailAutenticacao autenticacao : EmailAutenticacao.values()) {
			items.add(new SelectItem(autenticacao.name(), autenticacao
					.toString()));
		}
		return items;
	}

	public List<SelectItem> getListSegurancaoEmail() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (SegurancaEmail seg : SegurancaEmail.values()) {
			items.add(new SelectItem(seg.name(), seg.toString()));
		}
		return items;
	}

	@Override
	@RequestMapping({ "**/find_filial" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Filial();
	}

	@RequestMapping("**/findFilial")
	public void findFilial(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codFilial") Long codFilial)
			throws Exception {

			Filial filial = filialController.findUninqueByPropertyId(
					Filial.class, codFilial, "fil_codigo");
			if (filial != null) {
				httpServletResponse.getWriter().write(
						filial.getJson().toString());
			}

	}

	@RequestMapping("**/addBairroFilial")
	public void addBairroFilial(@RequestParam Long codBairro) throws Exception {
		if (codBairro != null && codBairro > 0) {
				objetoSelecionado.setBai_codigo(bairroController
						.findUninqueByProperty(Bairro.class, codBairro,
								"bai_codigo"));
		}
	}

	@RequestMapping("**/addCidadeFilial")
	public void addCidadeFilial(@RequestParam Long codCidade) throws Exception {

		if (codCidade != null && codCidade > 0) {
				objetoSelecionado.setCid_codigo(cidadeController
						.findUninqueByProperty(Cidade.class, codCidade,
								"cid_codigo"));
		}
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return filialController;
	}

	public CarregamentoLazyListForObject<Filial> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Filial> list) {
		this.list = list;
	}

	public Filial getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Filial objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public void excluir() throws Exception {
			if (objetoSelecionado.getFil_codigo() != null
					&& objetoSelecionado.getFil_codigo() > 0) {
				filialController.delete(objetoSelecionado);
				list.remove(objetoSelecionado);
				objetoSelecionado = new Filial();
				sucesso();
			}
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
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Filial();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}
}
