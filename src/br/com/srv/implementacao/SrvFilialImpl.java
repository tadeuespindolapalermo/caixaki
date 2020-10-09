package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryFilial;
import br.com.srv.interfaces.SrvFilial;

@Service
public class SrvFilialImpl implements SrvFilial {
	private static final long serialVersionUID = 1L;
	@Resource
	private RepositoryFilial repositoryFilial;

	public void setRepositoryFilial(RepositoryFilial repositoryFilial) {

		this.repositoryFilial = repositoryFilial;
	}
}