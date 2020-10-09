package br.com.project.bean.view;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.ComissaoFuncionarioFilialController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.FilialController;
import br.com.project.model.classes.ComissaoFuncionarioFilial;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.Filial;
import br.com.project.model.classes.ValorComissaoFuncionarioFilial;

@Controller
@Scope(value = "session")
@ManagedBean(name = "comissaoFuncionarioBeanView")
public class ComissaoFuncionarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<ComissaoFuncionarioFilial> list = new CarregamentoLazyListForObject<ComissaoFuncionarioFilial>();
	private ComissaoFuncionarioFilial objetoSelecionado = new ComissaoFuncionarioFilial();
	private String url = "/cadastro/cad_comissao.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_comissao.jsf?faces-redirect=true";
	private ValorComissaoFuncionarioFilial valorComissaoAddExluir = new ValorComissaoFuncionarioFilial();

	@Resource
	private ComissaoFuncionarioFilialController comissaoFuncionarioFilialController;
	
	@Resource
	private FilialController filialController;
	
	
	@Resource
	private EntidadeController entidadeController;
	
	
	public void addValor(){
		
		if (!(valorComissaoAddExluir.getVal_valor().doubleValue() == 0.0)) {
			if (objetoSelecionado.possuiPadrao()) {
				ValorComissaoFuncionarioFilial addicionar = new ValorComissaoFuncionarioFilial();
				addicionar.setVal_codigo(valorComissaoAddExluir.getVal_codigo());
				addicionar.setVal_valor(valorComissaoAddExluir.getVal_valor());
				addicionar.setVal_descricao(valorComissaoAddExluir.getVal_descricao());
				addicionar.setVal_calculo(valorComissaoAddExluir.getVal_calculo());
				addicionar.setVal_tipo(valorComissaoAddExluir.getVal_tipo());
				addicionar.setVal_comissao(objetoSelecionado);
				objetoSelecionado.addValorComissao(addicionar);  
				valorComissaoAddExluir = new ValorComissaoFuncionarioFilial();
			}else {
				addMsg("Já existe comissão padrão adicionada");
			}
		}
		else {
			addMsg("Valor inválido");
		}
	}

	
	
	public void excluirValor(){ 
		objetoSelecionado.removeValorComissao(valorComissaoAddExluir);
		valorComissaoAddExluir = new ValorComissaoFuncionarioFilial(); 
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
			objetoSelecionado = comissaoFuncionarioFilialController
					.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new ComissaoFuncionarioFilial();
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
		if (objetoSelecionado.getCom_codigo() != null
				&& objetoSelecionado.getCom_codigo() > 0) {
			comissaoFuncionarioFilialController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new ComissaoFuncionarioFilial();
			sucesso();
		}
	}

	/**
	 * Ivocado pelo botão consultar
	 */
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new ComissaoFuncionarioFilial();
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
	@RequestMapping({ "**/find_comissao" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		valorComissaoAddExluir = new ValorComissaoFuncionarioFilial();
		objetoSelecionado = new ComissaoFuncionarioFilial();
	}
	
	
	@RequestMapping("**/findFuncComissao")
	public void findUserDestino(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codFuncionario") Long codEntidade)
			throws Exception {
			Entidade entidade = entidadeController.findFuncionario(codEntidade);
			objetoSelecionado.setEnt_codigo(entidade);
			if (entidade != null) {
				httpServletResponse.getWriter().write(
						entidade.getJson().toString());
			}

	}
	
	
	@RequestMapping("**/addFuncComissao")
	public void addFuncComissao(@RequestParam Long codEntidade) throws Exception  {
		if (codEntidade != null && codEntidade > 0) {
				Entidade entidade = entidadeController
						.findFuncionario(codEntidade);
				objetoSelecionado.setEnt_codigo(entidade != null ? entidade
						: new Entidade());
		}
	}

	
	
	@RequestMapping("**/addFilialComissao")
	public void addFilialComissao(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codFilial") Long codFilial)
			throws Exception {

			Filial filial = filialController.findUninqueByPropertyId(
					Filial.class, codFilial, "fil_codigo");
			if (filial != null) {
				objetoSelecionado.setFil_codigo(filial); 
				httpServletResponse.getWriter().write(
						filial.getJson().toString());
			}

	}

	@Override
	protected Class<?> getClassImplement() {
		return ComissaoFuncionarioFilial.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return comissaoFuncionarioFilialController;
	}

	public CarregamentoLazyListForObject<ComissaoFuncionarioFilial> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<ComissaoFuncionarioFilial> list) {
		this.list = list;
	}

	public ComissaoFuncionarioFilial getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(ComissaoFuncionarioFilial objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}

	public ValorComissaoFuncionarioFilial getValorComissaoAddExluir() {
		return valorComissaoAddExluir;
	}

	public void setValorComissaoAddExluir(
			ValorComissaoFuncionarioFilial valorComissaoAddExluir) {
		this.valorComissaoAddExluir = valorComissaoAddExluir;
	}
	
}
