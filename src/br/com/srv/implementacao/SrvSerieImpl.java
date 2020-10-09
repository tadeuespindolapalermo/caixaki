package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositorySerie;
import br.com.srv.interfaces.SrvSerie;

@Service
public class SrvSerieImpl implements SrvSerie {
	private static final long serialVersionUID = 1L;
	@Resource
	private RepositorySerie repositorySerie;

	public void setRepositorySerie(RepositorySerie repositorySerie) {

		this.repositorySerie = repositorySerie;
	}
}