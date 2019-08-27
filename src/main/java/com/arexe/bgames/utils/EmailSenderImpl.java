package com.arexe.bgames.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j(topic = "bgames.logger")
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMessage(String to, String subject, String text) {
        final MimeMessage message = javaMailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
        } catch (MessagingException e) {
            log.error("[EMAIL_SENDER] E-mail could not be sent to " + to + ", subject: " + subject, e);
        }
        javaMailSender.send(message);
        log.info("[EMAIL_SENDER] E-mail sent to " + to + ", subject: " + subject);
    }
}
