package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Cidade;
import br.com.repository.interfaces.RepositoryCidade;
import br.com.srv.interfaces.SrvCidade;

@Controller
public class CidadeController extends ImplementacaoCrud<Cidade> implements
		InterfaceCrud<Cidade> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvCidade srvCidade;
	@Resource
	private RepositoryCidade repositoryCidade;

	public void setSrvCidade(SrvCidade srvCidade) {
		this.srvCidade = srvCidade;
	}

	public void setRepositoryCidade(RepositoryCidade repositoryCidade) {
		this.repositoryCidade = repositoryCidade;
	}

}
