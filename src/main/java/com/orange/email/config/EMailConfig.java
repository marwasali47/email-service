package com.orange.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Session;
import java.util.Properties;

@Configuration
public class EMailConfig {

    @Value("${mail.smtp.host}")
    private String smtpServerHost;

    @Value("${mail.smtp.port}")
    private Integer smtpServerPort;

    @Value("${mail.smtp.username}")
    private String smtpUserName;

    @Value("${mail.smtp.password}")
    private String smtpPassword;

    @Value("${proxy.host}")
    private String proxyHost;

    @Value("${proxy.port}")
    private Integer proxyPort;

    @Value("${proxy.enabled}")
    private boolean isProxyEnabled;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpServerHost);
        mailSender.setPort(smtpServerPort);
        mailSender.setUsername(smtpUserName);
        mailSender.setPassword(smtpPassword);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.host", smtpServerHost);
        props.put("mail.smtp.port", smtpServerPort);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.debug", "true");
        if(isProxyEnabled){
            props.put("mail.smtp.proxy.host", proxyHost);
            props.put("mail.smtp.proxy.port", proxyPort);
        }
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }
}
