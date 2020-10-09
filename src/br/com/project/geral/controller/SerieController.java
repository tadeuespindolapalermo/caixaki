package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Serie;
import br.com.repository.interfaces.RepositorySerie;
import br.com.srv.interfaces.SrvSerie;

@Controller
public class SerieController extends ImplementacaoCrud<Serie> implements
		InterfaceCrud<Serie> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvSerie srvSerie;
	@Resource
	private RepositorySerie repositorySerie;

	public void setSrvSerie(SrvSerie srvSerie) {
		this.srvSerie = srvSerie;
	}

	public void setRepositorySerie(RepositorySerie repositorySerie) {
		this.repositorySerie = repositorySerie;
	}

}
