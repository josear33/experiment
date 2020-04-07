package com.josear33.spring.kafka.spark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.josear33.spring.kafka.spark.service.CalculateRiskService;
import com.josear33.spring.kafka.spark.service.KafkaConsumer;

@SpringBootApplication
public class PatientRiskCalculatorApplication {

	@Autowired
	KafkaConsumer consumer;
	
	public static void main(String[] args) {
		SpringApplication.run(PatientRiskCalculatorApplication.class, args);
	}

}
