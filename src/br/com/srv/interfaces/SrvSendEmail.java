package br.com.srv.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import br.com.project.model.classes.SendEmail;

@Service
public interface SrvSendEmail extends Serializable {
	public Object enviarSimplesEmail(SendEmail sendEmail) throws Exception;

	public void sendEmail(SendEmail email) throws Exception;
}