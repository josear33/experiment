package com.josear33.spring.kafka.spark.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@Autowired
	CalculateRiskService riskService;

	private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "users", groupId = "group_id")
	public void consume(String message) throws IOException {
		riskService.getRiskScoring(message);
		logger.info(String.format("#### -> Consumed message -> %s", message));
	}
}
