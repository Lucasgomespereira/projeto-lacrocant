package com.lacrocant.lacrocant.infra.mailConfig;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.lacrocant.lacrocant.util.LaCrocanteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service("mailService")
public class MailService {

    @Autowired
	private JavaMailSender mailSender;

	@Async
	public void sendNewPassword(String email, String name, String password) throws IOException, LaCrocanteException {
		String subject = "Nova Senha";
		String content = "Olá " + name + "! <br>"
				+ "Como solicitado, estamos enviando uma nova senha de acesso: <strong>" + password + "</strong>";
		sendMail(email, subject, content);
	}

	public void sendMail(String to, String subject, String content) throws LaCrocanteException {
		try {
			final MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
					mimeMessage.setFrom(new InternetAddress("La'Crocante"));
					mimeMessage.setSubject(subject);
					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
					helper.setText("<html><body>" + content + "</body></html>", true);
				}
			};
			mailSender.send(preparator);
		} catch (MailException e) {
			System.out.println("E-mail não pode ser eviado!\n\n\n" + e.getMessage());
			e.printStackTrace();
			throw new LaCrocanteException(500, "E-mail não enviado!");
		}
	}
}