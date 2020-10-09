package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.SendEmailController;
import br.com.project.model.classes.SendEmail;

@Controller
@ManagedBean(name = "sendEmailBeanView")
@Scope("view")
public class SendEmailBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<SendEmail> list = new CarregamentoLazyListForObject<SendEmail>();
	private SendEmail objetoSelecionado = new SendEmail();

	@Autowired
	private SendEmailController sendEmailController;

	@Override
	protected Class<?> getClassImplement() {
		return SendEmail.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return sendEmailController;
	}

	public void sendEmail() throws Exception {
		SendEmail email = new SendEmail();
		email.setEmail("alex.fernando.egidio@gmail.com"); 
		email.setMsgEnviada("é um teste de email com spring ");
		sendEmailController.sendEmail(email);

	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return null;
	}
}
