package com.josear33.spring.kafka.rest.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josear33.spring.kafka.rest.producer.model.Persona;
import com.josear33.spring.kafka.rest.producer.service.Producer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
    }
    
    @PostMapping(value = "/followup")
    public void sendDiagFollowupToKafkaTopic(@RequestBody Persona persona) {
        this.producer.sendDiagFollowup(persona);
    }
}