
package ar.com.aleatoria.tools;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class Mailer {
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${mailer.from}")
    String from;

    public void sendMail(String to, String subject, String msg) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(msg, "text/html");
        mailSender.send(message); // Now we send the message
    }
}
