package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Serie;
import br.com.repository.interfaces.RepositorySerie;

@Repository
public class DaoSerie extends ImplementacaoCrud<Serie> implements
		RepositorySerie {
	
	private static final long serialVersionUID = 1L;
}