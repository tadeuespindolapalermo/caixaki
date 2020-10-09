package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.SendEmail;
import br.com.repository.interfaces.RepositorySendEmail;

@Repository 
public class DaoSendEmail extends ImplementacaoCrud<SendEmail> implements RepositorySendEmail {
private static final long serialVersionUID = 1L;}