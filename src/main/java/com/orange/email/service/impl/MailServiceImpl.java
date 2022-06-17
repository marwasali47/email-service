package com.orange.email.service.impl;

import com.orange.email.kafka.requests.SendMailKafkaRequest;
import com.orange.email.service.KafkaProducersService;
import com.orange.email.service.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender emailSender;

    private Logger logger = LogManager.getLogger(MailServiceImpl.class);

    @Value("${mail.smtp.from}")
    private String smtpFrom;

    @Value("${mail.smtp.port}")
    private String smtpPort;

    @Value("${mail.smtp.host}")
    private String smtpServerHost;

    @Value("${mail.smtp.username}")
    private String smtpUserName;

    @Value("${mail.smtp.password}")
    private String smtpPassword;

    private String reformatTextNewLines(String messageText) {
        return messageText.replace("\n", "<br/>");
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(smtpFrom);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(reformatTextNewLines(text),true);
            emailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Failed to send email " + e.getMessage());
        }
    }

    @Override
    public void sendMail(String to, String mailBody, String subject) {
        sendSimpleMessage(to,subject,mailBody);
    }
}
