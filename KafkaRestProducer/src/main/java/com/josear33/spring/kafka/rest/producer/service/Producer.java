package com.josear33.spring.kafka.rest.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josear33.spring.kafka.rest.producer.model.Persona;

@Service
@Async
public class Producer {

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private static final String TOPIC = "users";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		logger.info(String.format("#### -> Producing message -> %s", message));
		this.kafkaTemplate.send(TOPIC, message);
	}

	public void sendDiagFollowup(Persona persona) {
		try {
			logger.info(String.format("#### -> Producing message -> %s", objectMapper.writeValueAsString(persona)));
			this.kafkaTemplate.send(TOPIC,  objectMapper.writeValueAsString(persona));
		} catch (JsonProcessingException e) {
			logger.error(e.getStackTrace().toString());			
		} finally {
			// TODO: handle finally clause
		}

	}
}
