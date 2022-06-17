package com.orange.email.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.orange.email.kafka.requests.KafkaRequest;
import com.orange.email.service.KafkaProducersService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducersService {

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @Override
    public void sendKafkaRequest(String requestType, KafkaRequest request, String topic, String key) {
        Gson gson = new Gson();
        JsonObject requestJsonObject = new JsonObject();
        requestJsonObject.addProperty("type", requestType);
        requestJsonObject.addProperty("request", gson.toJson(request));
        kafkaTemplate.send(new ProducerRecord(topic, key, gson.toJson(requestJsonObject)));
    }
}
