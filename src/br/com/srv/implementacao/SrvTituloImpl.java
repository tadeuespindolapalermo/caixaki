package br.com.srv.implementacao;

import javax.annotation.Resource;

import br.com.repository.interfaces.RepositoryTitulo;
import br.com.srv.interfaces.SrvTitulo;

public class SrvTituloImpl implements SrvTitulo {
	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryTitulo repositoryTitulo;

	public void setRepositoryTitulo(RepositoryTitulo repositoryTitulo) {

		this.repositoryTitulo = repositoryTitulo;
	}
}