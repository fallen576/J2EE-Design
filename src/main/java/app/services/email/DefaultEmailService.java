package app.services.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import app.model.Reservation;
import app.model.User;

public class DefaultEmailService implements EmailService {
	
	private static final String FROM = "dealsonwheels.jhu@gmail.com";
	private static final String PASSWORD = "rF;aS44=";

	@Override
	public void send(User user, Reservation reservation) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props);		
		Message message = new MimeMessage(session);
		try {
			message.setSubject("Deals on Wheels Rental Car Confirmation #" + reservation.getConfirmationNumber());
			message.setContent(EmailContent.generate(user, reservation), "text/html");
			
			Address fromAddress = new InternetAddress(FROM);
			Address toAddress = new InternetAddress(user.getEmailAddress());
			message.setFrom(fromAddress);
			message.setRecipient(RecipientType.TO, toAddress);
			
			Transport transport = session.getTransport();
			transport.connect(FROM, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
