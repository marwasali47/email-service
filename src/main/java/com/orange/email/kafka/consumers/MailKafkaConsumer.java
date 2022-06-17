package com.orange.email.kafka.consumers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orange.email.entities.MailTemplate;
import com.orange.email.entities.MailTemplateVariable;
import com.orange.email.kafka.requests.SendMailKafkaRequest;
import com.orange.email.repository.MailTemplateRepository;
import com.orange.email.service.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.Collections;


@Component
public class MailKafkaConsumer {

    private Logger logger = LogManager.getLogger(MailKafkaConsumer.class);

    @Value("${kafka.bootstrap-servers}")
    private String bootStrapServers;

    @Value("${kafka.consumer.group.id}")
    private String consumerGroupId;

    @Value("${kafka.consumer.topic}")
    private String consumerTopic;

    @Autowired
    private MailTemplateRepository mailTemplateRepository;

    @Autowired
    private MailService mailService;


    @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "${kafka.consumer.group.id}")
    public void listenGroupFoo(String message) {
        System.out.println(message);


        JsonParser jsonParser = new JsonParser();
        com.google.gson.JsonObject requestJsonObject = jsonParser.parse(message).getAsJsonObject();
        logger.debug("send email kafka request received " + requestJsonObject.get("type").getAsString() );
        switch (requestJsonObject.get("type").getAsString()){
            case "sendMail":
                SendMailKafkaRequest request = new Gson().fromJson(requestJsonObject.get("request").getAsString(), SendMailKafkaRequest.class);
                logger.debug("send email kafka request processed template : " + request.mailTemplateName );
                handleSendMailRequest(request);
                break;
        }
    }

    private void handleSendMailRequest(SendMailKafkaRequest request) {
        MailTemplate mailTemplate = mailTemplateRepository
                .findByNameAndLanguageCode(request.mailTemplateName, request.languageCode);

        String mailBody = mailTemplate.getBody();
        for(String variable : request.variables.keySet()){
            String value = request.variables.get(variable);
            mailBody = mailBody.replace(variable, value);
        }
       mailService.sendMail(request.to, mailBody, mailTemplate.getSubject());
    }
}
