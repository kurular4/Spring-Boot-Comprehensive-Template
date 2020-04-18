package com.example.demo.model;

import com.example.demo.constant.MailContentType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@Accessors(chain = true)
public class Mail {

    @Email
    private String mailFrom;

    @Email
    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private String mailSubject;

    private String mailContent;

    private String contentType;

    private List<Object> attachments;

    public Mail(MailContentType mailContentType) {
        this.contentType = mailContentType.toString();
    }
}
