package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.EntidadeEntidade;
import br.com.repository.interfaces.RepositoryEntidadeEntidade;

@Repository
public class DaoEntidadeEntidade extends ImplementacaoCrud<EntidadeEntidade>
		implements RepositoryEntidadeEntidade {

	private static final long serialVersionUID = 1L;

	@Override
	public void removeAssociacao(Long ent_codigo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from EntidadeEntidade where ent_codvendedor = ");
		sql.append(ent_codigo);
		super.executeUpdateQueryDinamica(sql.toString());
	}
}