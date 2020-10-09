package br.com.srv.implementacao;

import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.com.project.model.classes.SendEmail;
import br.com.repository.interfaces.RepositorySendEmail;
import br.com.srv.interfaces.SrvSendEmail;

public class SrvSendEmailImpl implements SrvSendEmail {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MailSender mailSender = new JavaMailSenderImpl();

	@Autowired
	private SimpleMailMessage templateMessage = new SimpleMailMessage();

	@Resource
	private RepositorySendEmail repositorySendEmail;

	public void setRepositorySendEmail(RepositorySendEmail repositorySendEmail) {
		this.repositorySendEmail = repositorySendEmail;
	}

	@Override
	public Object enviarSimplesEmail(SendEmail sendEmail) throws Exception {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);

		msg.setTo(sendEmail.getEmail());
		msg.setSubject(templateMessage.getSubject());  
		msg.setFrom(templateMessage.getFrom());
		msg.setSentDate(Calendar.getInstance().getTime());

		msg.setText(sendEmail.getMsgEnviada());

		mailSender.send(msg);
		return true;
	}

	@Override
	public void sendEmail(SendEmail email) throws Exception {
		enviarSimplesEmail(email);
	}
}