package br.com.konoha.controllers.service;

import java.util.Date;

import br.com.konoha.model.transport.UserDTO;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

	private String sender;

	public AbstractEmailService(String sender) {
		this.sender = sender;
	}

	@Override
	public void sendNewPassword(UserDTO user, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(user, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(UserDTO user, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}

}
