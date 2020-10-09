package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.ComissaoFuncionarioFilial;
import br.com.repository.interfaces.RepositoryComissaoFuncionarioFilial;

@Repository
public class DaoComissaoFuncionarioFilial extends
		ImplementacaoCrud<ComissaoFuncionarioFilial> implements
		RepositoryComissaoFuncionarioFilial {

	private static final long serialVersionUID = 1L;
}