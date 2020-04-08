package com.josear33.spring.kafka.spark.service;

import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparkClusteringService {

	@Autowired
	private SQLContext sqlc;
	
}
