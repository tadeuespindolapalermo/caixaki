package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Bairro;
import br.com.repository.interfaces.RepositoryBairro;
import br.com.srv.interfaces.SrvBairro;

@Controller
public class BairroController extends ImplementacaoCrud<Bairro> implements
		InterfaceCrud<Bairro> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvBairro srvBairro;
	@Resource
	private RepositoryBairro repositoryBairro;

	public void setSrvBairro(SrvBairro srvBairro) {
		this.srvBairro = srvBairro;
	}

	public void setRepositoryBairro(RepositoryBairro repositoryBairro) {
		this.repositoryBairro = repositoryBairro;
	}

}
