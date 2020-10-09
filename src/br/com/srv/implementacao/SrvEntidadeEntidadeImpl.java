package br.com.srv.implementacao;

import javax.annotation.Resource;

import br.com.repository.interfaces.RepositoryEntidadeEntidade;
import br.com.srv.interfaces.SrvEntidadeEntidade;

public class SrvEntidadeEntidadeImpl implements SrvEntidadeEntidade {
	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryEntidadeEntidade repositoryEntidadeEntidade;

	public void setRepositoryEntidadeEntidade(
			RepositoryEntidadeEntidade repositoryEntidadeEntidade) {

		this.repositoryEntidadeEntidade = repositoryEntidadeEntidade;
	}
}