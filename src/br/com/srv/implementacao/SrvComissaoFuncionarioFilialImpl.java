package br.com.srv.implementacao;

import javax.annotation.Resource;

import br.com.repository.interfaces.RepositoryComissaoFuncionarioFilial;
import br.com.srv.interfaces.SrvComissaoFuncionarioFilial;

public class SrvComissaoFuncionarioFilialImpl implements
		SrvComissaoFuncionarioFilial {

	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryComissaoFuncionarioFilial repositoryComissaoFuncionarioFilial;

	public void setRepositoryComissaoFuncionarioFilial(
			RepositoryComissaoFuncionarioFilial repositoryComissaoFuncionarioFilial) {

		this.repositoryComissaoFuncionarioFilial = repositoryComissaoFuncionarioFilial;
	}
}