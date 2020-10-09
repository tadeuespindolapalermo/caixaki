package br.com.dao.implementacao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Titulo;
import br.com.repository.interfaces.RepositoryTitulo;

@Repository
public class DaoTitulo extends ImplementacaoCrud<Titulo> implements
		RepositoryTitulo {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Map<String, Object>> getMediaPorOrigem(int dias) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) as quantidade, tit_origem as situacao, trunc(avg(coalesce(tit_valor, 0.00)),2 ) as media_valor ");
		sql.append(" from titulo ");
		sql.append(" where  cast(tit_datahora as date) > (current_date -");
		sql.append(dias);
		sql.append(" ) and  cast(tit_datahora as date) <= current_date ");
		sql.append(" group by tit_origem order by quantidade; ");
		return super.getSimpleJdbcTemplate().queryForList(sql.toString());
	}

	@Override
	public List<Map<String, Object>> getMediaPorTipoReceberPagar(int dias) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) as quantidade, tit_tipo as situacao, trunc(avg(coalesce(tit_valor, 0.00)),2 ) as media_valor ");
		sql.append("  from titulo  ");
		sql.append("  where  cast(tit_datahora as date) > (current_date - ");
		sql.append(dias);
		sql.append(" ) and  cast(tit_datahora as date) <= current_date  ");
		sql.append("  group by tit_tipo;  ");
		return super.getSimpleJdbcTemplate().queryForList(sql.toString());
	}

	@Override
	public List<Map<String, Object>> getMediaPorTipoAbertoFechado(int dias) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) as quantidade, case when tit_baixado then 'BAIXADO' else 'EM ABERTO' end as situacao, trunc(avg(coalesce(tit_valor, 0.00)),2 ) as media_valor ");
		sql.append("  from titulo ");
		sql.append("  where  cast(tit_datahora as date) > (current_date - ");
		sql.append(dias);
		sql.append(" ) and  cast(tit_datahora as date) <= current_date ");
		sql.append(" group by tit_baixado; ");
		return super.getSimpleJdbcTemplate().queryForList(sql.toString());
	}

	@Override
	public List<Map<String, Object>> getTitulosUltimosDias(int dias) {
		StringBuilder sql = new StringBuilder();
		sql.append("   (SELECT count(1) AS quantidade,                                      ");
		sql.append("           tit_origem AS situacao,                                      ");
		sql.append("           trunc(avg(coalesce(tit_valor, 0.00)),2) AS media_valor       ");
		sql.append("    FROM titulo                                                         ");
		sql.append("    WHERE cast(tit_datahora AS date) > (CURRENT_DATE -  " + dias + ")   ");
		sql.append("      AND cast(tit_datahora AS date) <= CURRENT_DATE                    ");
		sql.append("    GROUP BY tit_origem                                                 ");
		sql.append("    UNION SELECT count(1) AS quantidade,                                ");
		sql.append("                 CASE                                                   ");
		sql.append("                     WHEN tit_baixado THEN 'BAIXADO'                    ");
		sql.append("                     ELSE 'EM ABERTO'                                   ");
		sql.append("                 END AS situacao,                                       ");
		sql.append("                 trunc(avg(coalesce(tit_valor, 0.00)),2) AS media_valor ");
		sql.append("    FROM titulo                                                         ");
		sql.append("    WHERE cast(tit_datahora AS date) > (CURRENT_DATE - " + dias + ")    ");
		sql.append("      AND cast(tit_datahora AS date) <= CURRENT_DATE                    ");
		sql.append("    GROUP BY tit_baixado                                                ");
		sql.append("    UNION SELECT count(1) AS quantidade,                                ");
		sql.append("                 tit_tipo AS situacao,                                  ");
		sql.append("                 trunc(avg(coalesce(tit_valor, 0.00)),2) AS media_valor ");
		sql.append("    FROM titulo                                                         ");
		sql.append("    WHERE cast(tit_datahora AS date) > (CURRENT_DATE - " + dias + ")    ");
		sql.append("      AND cast(tit_datahora AS date) <= CURRENT_DATE                    ");
		sql.append("    GROUP BY tit_tipo)                                                  ");
		sql.append(" ORDER BY quantidade,                                                   ");
		sql.append("          media_valor                                                   ");

		return super.getSimpleJdbcTemplate().queryForList(sql.toString());
	}

}