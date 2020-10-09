package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.ComissaoFuncionarioFilial;
import br.com.repository.interfaces.RepositoryComissaoFuncionarioFilial;
import br.com.srv.interfaces.SrvComissaoFuncionarioFilial;

@Controller
public class ComissaoFuncionarioFilialController extends
		ImplementacaoCrud<ComissaoFuncionarioFilial> implements
		InterfaceCrud<ComissaoFuncionarioFilial> {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvComissaoFuncionarioFilial srvComissaoFuncionarioFilial;
	
	@Resource
	private RepositoryComissaoFuncionarioFilial repositoryComissaoFuncionarioFilial;

	public void setSrvComissaoFuncionarioFilial(
			SrvComissaoFuncionarioFilial srvComissaoFuncionarioFilial) {
		this.srvComissaoFuncionarioFilial = srvComissaoFuncionarioFilial;
	}

	public void setRepositoryComissaoFuncionarioFilial(
			RepositoryComissaoFuncionarioFilial repositoryComissaoFuncionarioFilial) {
		this.repositoryComissaoFuncionarioFilial = repositoryComissaoFuncionarioFilial;
	}

}
