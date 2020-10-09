package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Filial;
import br.com.repository.interfaces.RepositoryFilial;

@Repository
public class DaoFilial extends ImplementacaoCrud<Filial> implements
		RepositoryFilial {
	private static final long serialVersionUID = 1L;
}