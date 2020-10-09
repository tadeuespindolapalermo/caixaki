package br.com.dao.implementacao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Mensagem;
import br.com.repository.interfaces.RepositoryMensagem;

@Repository
public class DaoMensagem extends ImplementacaoCrud<Mensagem> implements
		RepositoryMensagem {
	
	private static final long serialVersionUID = 1L;

	@Override
	public int possuiMsgNaoLidas(Long ent_codigo) {
		String sql = "select count(1) from mensagem where men_lido = false and usr_destino = ?";
		return super.getJdbcTemplate().queryForInt(sql, ent_codigo);
	}

	@Override
	public List<Mensagem> getMsgNaoLidas(Long ent_codigo, boolean isLidas)
			throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(" select m from Mensagem m where (m.men_lido = ");
		query.append(Boolean.toString(isLidas));
		query.append(" or m.men_lido = null) ");
		query.append(" and m.usr_destino.ent_codigo = ").append(ent_codigo);
		query.append(" order by m.men_datahora desc ");
		return super.findListByQueryDinamica(query.toString());
	}

	@Override
	public List<Mensagem> getMsgNaoLidas(Long ent_codigo) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(" select m from Mensagem m where ");
		query.append(" m.usr_destino.ent_codigo = ").append(ent_codigo);
		query.append(" order by m.men_datahora desc ");
		return super.findListByQueryDinamica(query.toString());
	}
}