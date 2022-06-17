package com.orange.email.service;

import java.util.Map;

public interface MailService {

    void sendMail(String to, String mailBody, String subject);
}
