package com.example.demo.service;

import com.example.demo.model.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender mailSender;

    public void sendEmail(Mail mail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        setAttributes(mimeMessageHelper, mail);
        mailSender.send(mimeMessageHelper.getMimeMessage());
    }

    private void setAttributes(MimeMessageHelper mimeMessageHelper, Mail mail) throws MessagingException {
        mimeMessageHelper.setSubject(mail.getMailSubject());
        mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
        mimeMessageHelper.setTo(mail.getMailTo());
        mimeMessageHelper.setText(mail.getMailContent());
    }
}
