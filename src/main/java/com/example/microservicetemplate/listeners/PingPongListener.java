package com.example.microservicetemplate.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PingPongListener
{

    Logger LOG = LoggerFactory.getLogger(PingPongListener.class);
    @KafkaListener(topics = "ping")
    void listener(String data) {
        LOG.info(data);
    }

}
