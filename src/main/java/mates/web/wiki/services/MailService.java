package mates.web.wiki.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    private final String fromAddress = "hello@mates.sbs";
    private final String senderName = "LandMates";

    MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String toAddress, String subject, String content, boolean html) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, html);
            mailSender.send(message);
        } catch (UnsupportedEncodingException | MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
