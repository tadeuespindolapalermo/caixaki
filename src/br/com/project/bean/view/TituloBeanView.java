package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.TituloSituacao;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.TituloController;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.Titulo;

@Controller
@ManagedBean(name = "tituloBeanView")
@Scope("session")
public class TituloBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Titulo> list = new CarregamentoLazyListForObject<Titulo>();
	private List<Titulo> listBaixar = new ArrayList<Titulo>();
	private Titulo objetoSelecionado = new Titulo();
	private String url = "/cadastro/financeiro/cad_titulo.jsf?faces-redirect=true";
	private String urlBaixado = "/cadastro/financeiro/cad_titulo_baixado.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/financeiro/find_titulo.jsf?faces-redirect=true";
	private String urlBaixar = "/cadastro/financeiro/baixar_titulo.jsf?faces-redirect=true";

	@Resource
	private TituloController tituloController;

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;

	public void setTituloController(TituloController tituloController) {
		this.tituloController = tituloController;
	}

	public TituloController getTituloController() {
		return tituloController;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		objetoSelecionado.setEnt_codigoabertura(contextoBean
				.getEntidadeLogada());
		return url;
	}

	@RequestMapping("**/findResponsavel")
	public void findResponsavel(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codResponsavel") Long codResponsavel)
			throws Exception {
			Entidade entidade = entidadeController.findUninqueByPropertyId(
					Entidade.class, codResponsavel, "ent_codigo");
			if (entidade != null) {
				httpServletResponse.getWriter().write(
						entidade.getJson().toString());
			}

	}

	@RequestMapping("**/addResponsavelTitulo")
	public void addResponsavelTitulo(@RequestParam Long codResponsavel)
			throws Exception {

		if (codResponsavel != null && codResponsavel > 0) {
				objetoSelecionado.setEnt_codigo(entidadeController
						.findUninqueByPropertyId(Entidade.class,
								codResponsavel, "ent_codigo"));
		}
	}

	@Override
	@RequestMapping({ "**/find_titulo", "**/find_baixa_titulo" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		listBaixar.clear();
		objetoSelecionado = new Titulo();
	}

	@Override
	public void saveNotReturn() throws Exception {
			if (validarCampoObrigatorio(objetoSelecionado)) {
				validarEntidadeBaixa();
				list.clear();
				listBaixar.clear();
				objetoSelecionado = tituloController.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				objetoSelecionado = new Titulo();
				objetoSelecionado.setEnt_codigoabertura(contextoBean
						.getEntidadeLogada());
				sucesso();
			}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}


	private void validarEntidadeBaixa() {
		if (objetoSelecionado.getEnt_codigobaixa() == null
				|| (objetoSelecionado.getEnt_codigobaixa() != null && objetoSelecionado
						.getEnt_codigobaixa().getEnt_codigo() == null)
				|| (objetoSelecionado.getEnt_codigobaixa().getEnt_codigo() != null && objetoSelecionado
						.getEnt_codigobaixa().getEnt_codigo() < 0)) {
			objetoSelecionado.setEnt_codigobaixa(null);
		}
	}

	@Override
	public void excluir() throws Exception {
			if (objetoSelecionado.getTit_codigo() != null
					&& objetoSelecionado.getTit_codigo() > 0) {
				tituloController.delete(objetoSelecionado);
				list.remove(objetoSelecionado);
				objetoSelecionado = new Titulo();
				objetoSelecionado.setEnt_codigoabertura(contextoBean
						.getEntidadeLogada());
				sucesso();
			}
	}

	@Override
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Titulo();
			objetoSelecionado.setEnt_codigoabertura(contextoBean
					.getEntidadeLogada());
			list.clear();
			listBaixar.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		listBaixar.clear();
		
		if (objetoSelecionado.getTit_baixado() != null && objetoSelecionado.getTit_baixado()){
			return urlBaixado;
		}
		
		return url;
	}

	public String baixa() throws Exception {
		objetoSelecionado.setEnt_codigobaixa(contextoBean.getEntidadeLogada());
		objetoSelecionado.setTit_databaixa(new Date());
		return urlBaixar;
	}

	@Override
	protected Class<Titulo> getClassImplement() {
		return Titulo.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return tituloController;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		if (getTituloSituacaoTemp() != null
				&& getTituloSituacaoTemp().equals(TituloSituacao.TITULO_ABERTO)) {
			return consultarInativosBoolean() ? " and entity.tit_baixado = false " : "and entity.ent_codigo.ent_inativo is false and entity.tit_baixado = false ";
		}
		return consultarInativosBoolean() ? "" : "and entity.ent_codigo.ent_inativo is false ";
	}

	public CarregamentoLazyListForObject<Titulo> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Titulo> list) {
		this.list = list;
	}

	public Titulo getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Titulo objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public List<SelectItem> getListTipoTitulo() {
		return tituloController.getListTipoTitulo();
	}

	public List<SelectItem> getListTipoTituloOrigem() {
		return tituloController.getListTipoTituloOrigem();
	}

	public String excluirDaBaixa() {
		listBaixar.remove(objetoSelecionado);
		objetoSelecionado = new Titulo();
		return urlBaixar;
	}

	public String baixarSelecionados() {
		listBaixar.clear();
		objetoSelecionado = new Titulo();
		for (Titulo titulo : list) {
			if (titulo.isSelecionarParaBaixa()) {
				listBaixar.add(titulo);
			}
		}
		if (!listBaixar.isEmpty()) {
			return urlBaixar;
		} else {
			return null;
		}
	}

	public List<Titulo> getListBaixar() {
		return listBaixar;
	}

	public void setListBaixar(List<Titulo> listBaixar) {
		this.listBaixar = listBaixar;
	}

	public String efetuarBaixa() throws Exception {
			objetoSelecionado.setEnt_codigobaixa(contextoBean
					.getEntidadeLogada());
			objetoSelecionado.setTit_databaixa(new Date());
			objetoSelecionado.setTit_baixado(Boolean.TRUE);
			int posicao = listBaixar.lastIndexOf(objetoSelecionado);
			objetoSelecionado = tituloController.merge(objetoSelecionado);
			listBaixar.remove(posicao);
			//listBaixar.add(posicao, objetoSelecionado);
			objetoSelecionado = new Titulo();
			addMsg("Baixa realizada com sucesso.");
		return urlBaixar;

	}

}
