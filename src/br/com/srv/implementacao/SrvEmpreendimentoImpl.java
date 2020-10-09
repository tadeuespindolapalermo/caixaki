package br.com.srv.implementacao;

import javax.annotation.Resource;

import br.com.repository.interfaces.RepositoryEmpreendimento;
import br.com.srv.interfaces.SrvEmpreendimento;

public class SrvEmpreendimentoImpl implements SrvEmpreendimento {
	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryEmpreendimento repositoryEmpreendimento;

	public void setRepositoryEmpreendimento(
			RepositoryEmpreendimento repositoryEmpreendimento) {

		this.repositoryEmpreendimento = repositoryEmpreendimento;
	}
}