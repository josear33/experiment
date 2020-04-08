package com.josear33.spring.kafka.spark.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@Autowired
	CalculateRiskService riskService;

	@KafkaListener(topics = "users", groupId = "group_id")
	public void consume(String message) throws IOException {
		riskService.getRiskScoring(message);
	}
}
