package br.com.konoha.controllers.service;

import br.com.konoha.model.transport.UserDTO;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	void sendEmail(SimpleMailMessage message);
	
	void sendNewPassword(UserDTO user, String newPassword);

}
