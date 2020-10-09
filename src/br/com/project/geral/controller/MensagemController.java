package br.com.project.geral.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Mensagem;
import br.com.repository.interfaces.RepositoryMensagem;
import br.com.srv.interfaces.SrvMensagem;

@Controller
public class MensagemController extends ImplementacaoCrud<Mensagem> implements
		InterfaceCrud<Mensagem> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvMensagem srvMensagem;
	@Resource
	private RepositoryMensagem repositoryMensagem;

	public void setSrvMensagem(SrvMensagem srvMensagem) {
		this.srvMensagem = srvMensagem;
	}

	public void setRepositoryMensagem(RepositoryMensagem repositoryMensagem) {
		this.repositoryMensagem = repositoryMensagem;
	}

	public int possuiMsgNaoLidas(Long ent_codigo) {
		return repositoryMensagem.possuiMsgNaoLidas(ent_codigo);
	}

	public List<Mensagem> getMsgNaoLidas(Long ent_codigo, boolean isLidas)
			throws Exception {
		return repositoryMensagem.getMsgNaoLidas(ent_codigo, isLidas);
	}

	public List<Mensagem> getMsgNaoLidas(Long ent_codigo) throws Exception {
		return repositoryMensagem.getMsgNaoLidas(ent_codigo);
	}

	public Mensagem confirmaLeituraMsg(Mensagem objetoSelecionado) throws Exception {
		if (objetoSelecionado.getMen_exigirresposta()){
			Mensagem mensagemConfirmaLeitura = new Mensagem();
			mensagemConfirmaLeitura.setMen_assunto(objetoSelecionado.getUsr_destino().getEnt_nomefantasia() + ", Confirmação de leitura");
			mensagemConfirmaLeitura.setMen_exigirresposta(false);
			mensagemConfirmaLeitura.setMen_lido(false);
			mensagemConfirmaLeitura.setUsr_destino(objetoSelecionado.getUsr_origem());
			mensagemConfirmaLeitura.setUsr_origem(objetoSelecionado.getUsr_destino());
			
			StringBuilder builderMsg = new StringBuilder();
			builderMsg.append("*******************Confirmação de leitura*******************\n");
			builderMsg.append("Assunto: ");
			builderMsg.append(objetoSelecionado.getMen_assunto()).append("\n\n");
			builderMsg.append("Mensagem enviada:\n");
			builderMsg.append("=============================================\n"); 
			builderMsg.append(objetoSelecionado.getMen_mensagem()).append("\n");
			builderMsg.append("=============================================\n");
			mensagemConfirmaLeitura.setMen_mensagem(builderMsg.toString());
			save(mensagemConfirmaLeitura);
		}
		
		 objetoSelecionado.setMen_lido(true);
		 //desmarca para não repetir o envio de msg lida caso o usuario leia novamente a msg
		 objetoSelecionado.setMen_exigirresposta(false);
		
		 return merge(objetoSelecionado);
	}
}
