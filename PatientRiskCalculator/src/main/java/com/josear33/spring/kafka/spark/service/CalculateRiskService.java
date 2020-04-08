package com.josear33.spring.kafka.spark.service;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateRiskService {


	private final Logger logger = LoggerFactory.getLogger(CalculateRiskService.class);
	
	@Autowired
	private SQLContext sqlc;

	public Long getRiskScoring(String persona) {
//		Dataset<Row> message = sqlContext.read().format("kafka").option("kafka.bootstrap.servers", "localhost:9092")
//				.option("subscribe", "users").load();

		List<String> data = new ArrayList<String>();
		data.add(persona);
		Dataset<String> df = sqlc.createDataset(data,Encoders.STRING());
		
		logger.info(String.format("####-#### -> Spark response from kafka -> %s", df.first()+": "+df.count()));
		return 60 + (long) (Math.random() * (60 - 100));
=======
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateRiskService {


	private final Logger logger = LoggerFactory.getLogger(CalculateRiskService.class);
	
	@Autowired
	private JavaSparkContext sc;

	public Long getRiskScoring(String Persona) {
//		Dataset<Row> message = sqlContext.read().format("kafka").option("kafka.bootstrap.servers", "localhost:9092")
//				.option("subscribe", "users").load();

		SQLContext sqlc = new SQLContext(SparkSession.builder().getOrCreate());
		sqlc.readStream()
		logger.info(String.format("####-#### -> Spark response from kafka -> %s", message.count()+": "+message.toString()));
		return message.count();
>>>>>>> branch 'master' of https://github.com/josear33/experiment
	}

}
