package com.example.demo;

import com.example.demo.constant.MailContentType;
import com.example.demo.model.Mail;
import com.example.demo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    MailService mailService;

    //TODO
    @Test
    public void sendProperEmail() throws MessagingException {
        mailService.sendEmail(new Mail(MailContentType.PLAIN_TEXT)
        .setMailSubject("trial")
        .setMailTo("")
        .setMailFrom("")
                .setMailContent("Hi, this is to try mail sender service"));
    }
}
