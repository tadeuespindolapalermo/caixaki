package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.acessos.Permissao;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.TipoCadastro;
import br.com.project.enums.TipoPessoa;
import br.com.project.geral.controller.BairroController;
import br.com.project.geral.controller.CidadeController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.FilialController;
import br.com.project.model.classes.Bairro;
import br.com.project.model.classes.Cidade;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.Filial;

@Controller
@Scope(value = "session")
@ManagedBean(name = "funcionarioBeanView")
public class FuncionarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();
	private Entidade objetoSelecionado = new Entidade();
	private String url = "/cadastro/cad_funcionario.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_funcionario.jsf?faces-redirect=true";
	private String urlPermissao = "/cadastro/cad_permissao.jsf?faces-redirect=true";
	private List<Permissao> listSelecionado = new ArrayList<Permissao>();
	private DualListModel<Permissao> listMenu = new DualListModel<Permissao>();

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;

	@Autowired
	private CidadeController cidadeController;

	@Autowired
	private BairroController bairroController;

	@Autowired
	private FilialController filialController;
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_funcionario");
		super.setNomeRelatorioSaida("report_funcionario");
		List<?> list = entidadeController.findListByProperty(Entidade.class, "ent_tipo", "TIPO_CADASTRO_FUNCIONARIO");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	public String getUsuarioLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}
	
	public DualListModel<Permissao> getListMenu() {
				permissao();
				setListMenu(new DualListModel<Permissao>(Permissao.getListPermissao(),
				listSelecionado));
				
				for (Permissao acesso :  listSelecionado){
					if (listMenu.getSource().contains(acesso)){
						listMenu.getSource().remove(acesso); 
					}
				}
				
		return listMenu;
	}

	public List<Permissao> getListSelecionado() {
		return listSelecionado;
	}
 
	public void setListSelecionado(List<Permissao> listSelecionado) {
		this.listSelecionado = listSelecionado;
	}

	public void setListMenu(DualListModel<Permissao> listMenu) {
		this.listMenu = listMenu;
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		return url;
	}

	public Date getUltimoAcesso() throws Exception {
		return contextoBean.getEntidadeLogada().getEnt_ultimoacesso();
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@Override
	@RequestMapping({ "**/find_funcionario" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Entidade();
	}

	public CarregamentoLazyListForObject<Entidade> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Entidade> list) {
		this.list = list;
	}

	@Override
	protected Class<?> getClassImplement() {
		return Entidade.class;
	}
	
	public String permissao(){
		listSelecionado.clear();
		Iterator<Permissao> iterator = objetoSelecionado.getAcessosPermissao().iterator();
		while (iterator.hasNext()) {
			listSelecionado.add(iterator.next());
		} 
		
		Collections.sort(listSelecionado, new Comparator<Permissao>() { 

			@Override
			public int compare(Permissao o1, Permissao o2) {
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}
		});
		return urlPermissao;
	}
	
	public String savePermissoes() throws Exception {
			if (validarCampoObrigatorio(objetoSelecionado)) {
				list.clear();
				objetoSelecionado.getAcessos().clear();
				listSelecionado.clear();
				
				List<Permissao> permissoesConverter = getConvertPermissoes();
				
				for (Permissao permissao : permissoesConverter) {
					listSelecionado.add(permissao); 
					objetoSelecionado.getAcessos().add(permissao.name());
				}
				objetoSelecionado = entidadeController.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				sucesso(); 
			}
			return urlPermissao;
	}

	private List<Permissao> getConvertPermissoes() {
		List<Permissao> retorno = new ArrayList<Permissao>();
		Object[] acessos = (Object[]) listMenu.getTarget().toArray();
		
		for (Object object : acessos) {
			for (Permissao ace : Permissao.values()){
				if (object.toString().equals(ace.name())){
					retorno.add(ace);
				}
			}
		}
		return retorno;
	}

	@Override
	public void saveNotReturn() throws Exception {
			objetoSelecionado.setEnt_tipo(TipoCadastro.TIPO_CADASTRO_FUNCIONARIO);
			
			if (validarCampoObrigatorio(objetoSelecionado)) {
				
				if (entidadeController.existeUsuario(objetoSelecionado.getEnt_login())){
					addMsg("Já existe usuário com o mesmo login.");
					return;
				}
				
				gravaFuncionario();
			}
	}

	private void gravaFuncionario() throws Exception {
		list.clear();
		List<Permissao> permissoesConverter = getConvertPermissoes(); 
		for (Permissao permissao : permissoesConverter) { 
			objetoSelecionado.getAcessos().add(permissao.name());
		}
		if (!objetoSelecionado.getAcessos().contains("USER")){
			objetoSelecionado.getAcessos().add("USER");
		}
		objetoSelecionado = entidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Entidade();
		sucesso();
	}
	
	@Override
	public void saveEdit() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			gravaFuncionario();
		}
	}

	public List<SelectItem> getListTipoPessoa() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoPessoa tipoPessoa : TipoPessoa.values()) {
			items.add(new SelectItem(tipoPessoa.name(), tipoPessoa.toString()));
		}
		return items;
	}

	@RequestMapping("**/addBairroFunc")
	public void addBairroFilial(@RequestParam Long codBairro) throws Exception {
		if (codBairro != null && codBairro > 0) {
				objetoSelecionado.setBai_codigo(bairroController
						.findUninqueByProperty(Bairro.class, codBairro,
								"bai_codigo"));
		}
	}

	@RequestMapping("**/addCidadeFunc")
	public void addCidadeFilial(@RequestParam Long codCidade) throws Exception {

		if (codCidade != null && codCidade > 0) {
				objetoSelecionado.setCid_codigo(cidadeController
						.findUninqueByProperty(Cidade.class, codCidade,
								"cid_codigo"));
		}
	}

	@RequestMapping("**/addFilialFunc")
	public void addFilialEntidade(@RequestParam Long codFilial) throws Exception {

		if (codFilial != null && codFilial > 0) {
				objetoSelecionado.setFil_codigo(filialController
						.findUninqueByProperty(Filial.class, codFilial,
								"fil_codigo"));
		}
	}

	@Override
	public void excluir() throws Exception {
			if (objetoSelecionado.getEnt_codigo() != null
					&& objetoSelecionado.getEnt_codigo() > 0) {
				entidadeController.delete(objetoSelecionado);
				list.remove(objetoSelecionado);
				objetoSelecionado = new Entidade();
				sucesso();
			}
	}

	@Override
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Entidade();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}

	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "and entity.ent_tipo = '" + TipoCadastro.TIPO_CADASTRO_FUNCIONARIO.name()+ "' " + consultarInativos();
	}

}
