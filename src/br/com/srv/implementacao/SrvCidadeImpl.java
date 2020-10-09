package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryCidade;
import br.com.srv.interfaces.SrvCidade;

@Service
public class SrvCidadeImpl implements SrvCidade {
	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryCidade repositoryCidade;

	public void setRepositoryCidade(RepositoryCidade repositoryCidade) {

		this.repositoryCidade = repositoryCidade;
	}

}