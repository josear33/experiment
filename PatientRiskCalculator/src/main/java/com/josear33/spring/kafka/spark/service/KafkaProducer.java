package com.josear33.spring.kafka.spark.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.josear33.spring.kafka.spark.constants.ApplicationConstants;

@Service
@Async

public class KafkaProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void publishCalculatedRisk(String persona) {
		logger.info(String.format("#### -> Producing message -> %s", persona));
		this.kafkaTemplate.send(ApplicationConstants.RISKTOPIC, persona);
	}
}
