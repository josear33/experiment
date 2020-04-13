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
	@Autowired
	KafkaProducer producerService;
	private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);


	@KafkaListener(topics = "${kafka.topic.cluster.predict}", groupId = "${spring.kafka.consumer.group-id}")
	public void calculateRisk(String message) throws IOException {
		logger.info(String.format("#### -> Consuming message -> %s", message));
		producerService.publishCalculatedRisk(riskService.getRiskScoring(message));

	}
	
	@KafkaListener(topics = "${kafka.topic.cluster.train}", groupId = "${spring.kafka.consumer.group-id}")
	public void trainModel(String message) throws IOException {
		logger.info(String.format("#### -> Consuming message -> %s", message));
		producerService.publishTrainModel(riskService.trainModel(message));
	}
}
