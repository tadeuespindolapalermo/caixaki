package br.com.project.bean.view;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.EmpreendimentoController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Empreendimento;
import br.com.project.model.classes.Entidade;

@Controller
@ManagedBean(name = "empreendimentoBeanView")
@Scope("session")
public class EmpreendimentoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<Empreendimento> list = new CarregamentoLazyListForObject<Empreendimento>();
	private Empreendimento objetoSelecionado = new Empreendimento();
	private String url = "/cadastro/cad_empreendimento.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_empreendimento.jsf?faces-redirect=true";

	@Resource
	private EmpreendimentoController empreendimentoController;

	@Autowired
	private EntidadeController entidadeController;

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_empreendimento");
		super.setNomeRelatorioSaida("report_empreendimento");
		List<?> list = empreendimentoController.finListOrderByProperty(Empreendimento.class, "emp_id");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	@Override
	public void saveNotReturn() throws Exception {
			if (validarCampoObrigatorio(objetoSelecionado)) {
				list.clear();
				objetoSelecionado = empreendimentoController
						.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				objetoSelecionado = new Empreendimento();
				sucesso();
			}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public void excluir() throws Exception {
			if (objetoSelecionado.getEmp_id() != null
					&& objetoSelecionado.getEmp_id() > 0) {
				empreendimentoController.delete(objetoSelecionado);
				list.remove(objetoSelecionado);
				objetoSelecionado = new Empreendimento();
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
			objetoSelecionado = new Empreendimento();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	@RequestMapping({ "**/find_empreendimento" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Empreendimento();
	}

	@RequestMapping("**/addConstrutoraEmpreendimento")
	public void addConstrutoraEmpreendimento(@RequestParam Long codConstrutora)
			throws Exception {
		if (codConstrutora != null && codConstrutora > 0) {
				Entidade entidade = entidadeController
						.findContrutora(codConstrutora);
				objetoSelecionado.setEnt_codigo(entidade != null ? entidade
						: new Entidade());
		}
	}

	public void setEmpreendimentoController(
			EmpreendimentoController empreendimentoController) {
		this.empreendimentoController = empreendimentoController;
	}

	public EmpreendimentoController getEmpreendimentoController() {
		return empreendimentoController;
	}

	@Override
	protected Class<Empreendimento> getClassImplement() {
		return Empreendimento.class;
	}

	@Override
	protected InterfaceCrud<Empreendimento> getController() {
		return empreendimentoController;
	}

	public CarregamentoLazyListForObject<Empreendimento> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Empreendimento> list) {
		this.list = list;
	}

	public Empreendimento getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Empreendimento objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return consultarInativosBoolean() ? "" : " and entity.ent_codigo.ent_inativo is false ";
	}

}
