package br.com.project.geral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.SendEmail;
import br.com.repository.interfaces.RepositorySendEmail;
import br.com.srv.interfaces.SrvSendEmail;

@Controller
public class SendEmailController extends ImplementacaoCrud<SendEmail> implements
		InterfaceCrud<SendEmail> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SrvSendEmail srvSendEmail;
	@Autowired
	private RepositorySendEmail repositorySendEmail;

	public void setSrvSendEmail(SrvSendEmail srvSendEmail) {
		this.srvSendEmail = srvSendEmail;
	}

	public void setRepositorySendEmail(RepositorySendEmail repositorySendEmail) {
		this.repositorySendEmail = repositorySendEmail;
	}

	public void sendEmail(SendEmail email) throws Exception {
		srvSendEmail.sendEmail(email);
	}

}
