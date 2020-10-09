package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository
public interface RepositoryEntidadeEntidade  extends Serializable{

	void removeAssociacao(Long ent_codigo) throws Exception ;
}