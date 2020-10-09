package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Cidade;
import br.com.repository.interfaces.RepositoryCidade;

@Repository
public class DaoCidade extends ImplementacaoCrud<Cidade> implements
		RepositoryCidade {
	private static final long serialVersionUID = 1L;
}