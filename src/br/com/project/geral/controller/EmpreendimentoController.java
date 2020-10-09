package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Empreendimento;
import br.com.repository.interfaces.RepositoryEmpreendimento;
import br.com.srv.interfaces.SrvEmpreendimento;

@Controller
public class EmpreendimentoController extends ImplementacaoCrud<Empreendimento>
		implements InterfaceCrud<Empreendimento> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvEmpreendimento srvEmpreendimento;
	@Resource
	private RepositoryEmpreendimento repositoryEmpreendimento;

	public void setSrvEmpreendimento(SrvEmpreendimento srvEmpreendimento) {
		this.srvEmpreendimento = srvEmpreendimento;
	}

	public void setRepositoryEmpreendimento(
			RepositoryEmpreendimento repositoryEmpreendimento) {
		this.repositoryEmpreendimento = repositoryEmpreendimento;
	}

}
