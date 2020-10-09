package br.com.repository.interfaces;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.model.classes.Mensagem;

@Transactional
@Repository
public interface RepositoryMensagem  extends Serializable {

	int possuiMsgNaoLidas(Long ent_codigo);

	List<Mensagem> getMsgNaoLidas(Long ent_codigo, boolean isLidas)
			throws Exception;

	public List<Mensagem> getMsgNaoLidas(Long ent_codigo) throws Exception;
}