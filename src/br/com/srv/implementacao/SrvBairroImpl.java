package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryBairro;
import br.com.srv.interfaces.SrvBairro;

@Service
public class SrvBairroImpl implements SrvBairro {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryBairro repositoryBairro;

	public void setRepositoryBairro(RepositoryBairro repositoryBairro) {

		this.repositoryBairro = repositoryBairro;
	}
}