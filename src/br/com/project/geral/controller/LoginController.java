package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.srv.interfaces.SrvLogin;

@Controller
public class LoginController extends ImplementacaoCrud<Object> implements
		InterfaceCrud<Object> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvLogin srvLogin;

	public void setSrvLogin(SrvLogin srvLogin) {
		this.srvLogin = srvLogin;
	}

}
