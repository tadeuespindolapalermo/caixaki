package br.com.project.bean.view;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.MensagemController;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.Mensagem;
import br.com.project.util.all.Messagens;

@Controller
@Scope(value = "session")
@ManagedBean(name = "mensagemBeanView")
public class MensagemBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<Mensagem> list = new CarregamentoLazyListForObject<Mensagem>();
	private Mensagem objetoSelecionado = new Mensagem();
	private String url = "/cadastro/cad_mensagem.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_mensagem.jsf?faces-redirect=true";
	private String msgRecebidas = "/cadastro/msg_recebidas.jsf?faces-redirect=true";

	@Resource
	private MensagemController mensagemController;

	@Resource
	private ContextoBean contextoBean;

	@Resource
	private EntidadeController entidadeController;

	public void setMensagemController(MensagemController mensagemController) {
		this.mensagemController = mensagemController;
	}

	public MensagemController getMensagemController() {
		return mensagemController;
	}

	/**
	 * Executado automaticamente a cada 600 segundos pelo componente p:poll do
	 * primefaces
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "**/verificaMsgNaoLidas", method = RequestMethod.GET)
	public void verificar(
			HttpServletResponse httpServletResponse,
			HttpSession session,
			@RequestParam(value = "isProcessoFacesJsf") boolean isProcessoFacesJsf)
			throws Exception {
			Map<String, Integer> mapsResponse = new HashMap<String, Integer>();
			int quantMsg = 0;
			if (isProcessoFacesJsf) {
				quantMsg = mensagemController.possuiMsgNaoLidas(contextoBean
						.getEntidadeLogada().getEnt_codigo());
			} else {
				Entidade userLogadoSessao = (Entidade) session
						.getAttribute("userLogadoSessao");
				quantMsg = mensagemController
						.possuiMsgNaoLidas(userLogadoSessao.getEnt_codigo());
			}

			if (quantMsg > 0 && isProcessoFacesJsf) {
				Messagens.msgSeverityInfor("Voçe possui novas menssagens.");
			}

			mapsResponse.put("quantidadeMsgNaoLida", quantMsg);

			if (httpServletResponse != null) {
				httpServletResponse.getWriter().write(
						new JSONObject(mapsResponse).toString());
			}
	}

	public void verificar() throws Exception {
		verificar(null, null, true);
	}

	@Override
	public void saveNotReturn() throws Exception {
			if (objetoSelecionado.getUsr_destino().getEnt_codigo()
					.equals(objetoSelecionado.getUsr_origem().getEnt_codigo())) {
				Messagens
						.msgSeverityWarn("Origem não pode ser igual ao destino.");
				return;
			}

			if (objetoSelecionado.getUsr_destino() == null
					|| objetoSelecionado.getUsr_destino().getEnt_login() == null
					|| objetoSelecionado.getUsr_destino().getEnt_codigo() <= 0) {
				Messagens.msgSeverityWarn("Informe o usuário de destino.");
				return;
			}

			if (validarCampoObrigatorio(objetoSelecionado)) {
				list.clear();
				objetoSelecionado = mensagemController.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				objetoSelecionado = new Mensagem();
				objetoSelecionado.setUsr_origem(contextoBean
						.getEntidadeLogada());
				objetoSelecionado.setUsr_destino(new Entidade());
				sucesso();
			}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}


	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		objetoSelecionado = new Mensagem();
		objetoSelecionado.setUsr_origem(contextoBean.getEntidadeLogada());
		objetoSelecionado.setUsr_destino(new Entidade());
		return url;
	}

	@Override
	@RequestMapping({ "**/find_mensagem" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Mensagem();
	}
	
	
	@RequestMapping(value = "**/confirmaLeituraMsg", method= RequestMethod.GET)
	public void confirmaLeituraMsg(@RequestParam(value="men_codigo") Long men_codigo, HttpServletResponse httpServletResponse) throws Exception {
		
		 objetoSelecionado = mensagemController.findById(Mensagem.class, men_codigo);
		 
		 boolean exigeResposta = objetoSelecionado.getMen_exigirresposta();
		
		 int posicao = list.getList().lastIndexOf(objetoSelecionado);
		 list.remove(objetoSelecionado);
		 
		 objetoSelecionado = mensagemController.confirmaLeituraMsg(objetoSelecionado);
		 
		 list.getList().add(posicao, objetoSelecionado);
		 
		 if (exigeResposta) {// se precisar dar uma resposta
			 httpServletResponse.getWriter().write("confirmaLeitura");
		 }	 
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
		return url;
	}

	@Override
	protected Class<?> getClassImplement() {
		return Mensagem.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return mensagemController;
	}

	@Override
	public void excluir() throws Exception {
			if (objetoSelecionado.getMen_codigo() != null
					&& objetoSelecionado.getMen_codigo() > 0) {
				mensagemController.delete(objetoSelecionado);
				list.remove(objetoSelecionado);
				objetoSelecionado = new Mensagem();
				sucesso();
			}
	}

	@Override
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Mensagem();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@RequestMapping("**/addDestinoMsg")
	public void addDestinoMsg(@RequestParam Long codEntidade) throws Exception  {
		if (codEntidade != null && codEntidade > 0) {
				Entidade entidade = entidadeController
						.findFuncionario(codEntidade);
				objetoSelecionado.setUsr_destino(entidade != null ? entidade
						: new Entidade());
		}
	}

	public CarregamentoLazyListForObject<Mensagem> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Mensagem> list) {
		this.list = list;
	}

	public Mensagem getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Mensagem objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return " and entity.usr_origem.ent_codigo = "
				+ contextoBean.getEntidadeLogada().getEnt_codigo();
	}

	public String msgRecebidas() throws Exception {
		list.clear();
		objetoSelecionado = new Mensagem();
		list.addAll(mensagemController.getMsgNaoLidas(contextoBean
				.getEntidadeLogada().getEnt_codigo()));
		return msgRecebidas;
	}

	@RequestMapping({ "**/msg_recebidas" })
	public String lerMsg(HttpSession session) throws Exception {
		setarVariaveisNulas();
		list.clear();
		objetoSelecionado = new Mensagem();
		Entidade userLogadoSessao = (Entidade) session
				.getAttribute("userLogadoSessao");
		list.addAll(mensagemController.getMsgNaoLidas(userLogadoSessao
				.getEnt_codigo()));
		return null;
	}

	@RequestMapping(value = "**/marcarDesmarcarLido", method = RequestMethod.GET)
	public void marcarDesmarcarLido(
			@RequestParam(value = "men_codigo") Long men_codigo)
			throws Exception {
		objetoSelecionado = mensagemController.findById(Mensagem.class,
				men_codigo);
		int posicao = list.getList().lastIndexOf(objetoSelecionado);
		list.remove(objetoSelecionado);
		if (objetoSelecionado.getMen_lido()) {
			objetoSelecionado.setMen_lido(false);
		} else {
			objetoSelecionado.setMen_lido(true);
		}
		objetoSelecionado = mensagemController.merge(objetoSelecionado);
		list.getList().add(posicao, objetoSelecionado);
	}

	@RequestMapping(value = "**/responderMsg", method = RequestMethod.GET)
	public String responderMsg(@RequestParam(value = "destino") Long destinoCod, HttpSession session)
			throws Exception {
			list.clear();
			objetoSelecionado = new Mensagem();
			Entidade destino = entidadeController.findFuncionario(destinoCod);
			objetoSelecionado.setUsr_destino(destino);
			objetoSelecionado.setUsr_origem((Entidade) session.getAttribute("userLogadoSessao"));
		return url;
	}
	
	public Object onRowSelect(SelectEvent event) {
		objetoSelecionado = (Mensagem) event.getObject();
		return  null;
	}

	public Object onRowUnselect(UnselectEvent event) {
		objetoSelecionado = (Mensagem) event.getObject();
		return  null;
	}
}
