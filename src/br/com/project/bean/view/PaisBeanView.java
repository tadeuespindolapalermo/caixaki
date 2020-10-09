package br.com.project.bean.view;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.PaisController;
import br.com.project.model.classes.Pais;

@Controller
@Scope(value = "session")
@ManagedBean(name = "paisBeanView")
public class PaisBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<Pais> list = new CarregamentoLazyListForObject<Pais>();
	private Pais objetoSelecionado = new Pais();
	private String url = "/cadastro/cad_pais.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_pais.jsf?faces-redirect=true";

	@Resource
	private PaisController paisController;

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
				objetoSelecionado = paisController.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				objetoSelecionado = new Pais();
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
			if (objetoSelecionado.getPai_id() != null
					&& objetoSelecionado.getPai_id() > 0) {
				paisController.delete(objetoSelecionado);
				list.remove(objetoSelecionado);
				objetoSelecionado = new Pais();
				sucesso();
			}
	}

	/**
	 * Ivocado pelo botão consultar
	 */
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Pais();
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
	@RequestMapping({ "**/find_pais" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Pais();
	}

	@Override
	protected Class<?> getClassImplement() {
		return Pais.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return paisController;
	}

	public CarregamentoLazyListForObject<Pais> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Pais> list) {
		this.list = list;
	}

	public Pais getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Pais objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public PaisController getPaisController() {
		return paisController;
	}

	public void setPaisController(PaisController paisController) {
		this.paisController = paisController;
	}

	public List<SelectItem> getPaises() {
		return paisController.getListPais();
	}
	
	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}

}
