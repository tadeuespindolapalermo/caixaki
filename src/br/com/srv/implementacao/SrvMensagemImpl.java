package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryMensagem;
import br.com.srv.interfaces.SrvMensagem;

@Service
public class SrvMensagemImpl implements SrvMensagem {
	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryMensagem repositoryMensagem;

	public void setRepositoryMensagem(RepositoryMensagem repositoryMensagem) {

		this.repositoryMensagem = repositoryMensagem;
	}
}