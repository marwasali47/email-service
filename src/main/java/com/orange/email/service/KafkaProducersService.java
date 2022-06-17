package com.orange.email.service;


import com.orange.email.kafka.requests.KafkaRequest;


public interface KafkaProducersService {
    void sendKafkaRequest(String requestType, KafkaRequest request, String topic, String key);
}
