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
import br.com.project.geral.controller.EstadoController;
import br.com.project.geral.controller.PaisController;
import br.com.project.model.classes.Estado;
import br.com.project.model.classes.Pais;

@Controller
@Scope(value = "session")
@ManagedBean(name = "estadoBeanView")
public class EstadoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<Estado> list = new CarregamentoLazyListForObject<Estado>();
	private Estado objetoSelecionado = new Estado();
	private String url = "/cadastro/cad_estado.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_estado.jsf?faces-redirect=true";

	@Resource
	private EstadoController estadoController;
	
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
				if (objetoSelecionado.getPais() == null || (objetoSelecionado.getPais() != null && objetoSelecionado.getPais().getPai_id() == null)) {
					// como o pais está ocultado na tela procura pelo brasil cadastrado no banco de dados, visto que na implantação do sistema o mesmo insere um registro de pais BRASIL
					Pais pais = paisController.findUninqueByProperty(Pais.class, "BRASIL", "pai_nome"); 
					objetoSelecionado.setPais(pais);
				}
				list.clear();
				objetoSelecionado = estadoController.merge(objetoSelecionado);
				list.getList().add(objetoSelecionado);
				objetoSelecionado = new Estado();
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
			if (objetoSelecionado.getEst_id() != null
					&& objetoSelecionado.getEst_id() > 0) {
				estadoController.delete(objetoSelecionado);
				list.getList().remove(objetoSelecionado);
				objetoSelecionado = new Estado();
				sucesso();
			}
	}

	/**
	 * Ivocado pelo botão consultar
	 */
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Estado();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	public CarregamentoLazyListForObject<Estado> getList() {
		return list;
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
	@RequestMapping({ "**/find_estado" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Estado();
	}

	@Override
	protected Class<?> getClassImplement() {
		return Estado.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return estadoController;
	}

	public List<SelectItem> getEstados() {
		return estadoController.getListEstado();
	}

	public Estado getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Estado objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}

}
