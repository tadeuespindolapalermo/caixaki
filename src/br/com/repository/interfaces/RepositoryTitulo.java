package br.com.repository.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RepositoryTitulo  extends Serializable{

	List<Map<String, Object>> getMediaPorOrigem(int dias);

	List<Map<String, Object>> getMediaPorTipoReceberPagar(int dias);

	List<Map<String, Object>> getMediaPorTipoAbertoFechado(int dias);
	
	List<Map<String, Object>> getTitulosUltimosDias(int dias);
}