package com.orange.email.kafka.requests;

import java.util.Map;


public class SendMailKafkaRequest extends KafkaRequest {

    public String to;
    public String mailTemplateName;
    public String languageCode;
    public Map<String, String> variables;
}
