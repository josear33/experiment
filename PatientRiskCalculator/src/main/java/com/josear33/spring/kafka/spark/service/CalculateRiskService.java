package com.josear33.spring.kafka.spark.service;

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
	@Autowired
	private SparkClusteringService sparkClusteringService;

	private List<String> messageBuffer = new ArrayList<String>();

	public String getRiskScoring(String persona) {
		logger.info("Begin clustering");
		List<String> data = new ArrayList<String>();
		data.add(persona);
		Dataset<String> df = sqlc.createDataset(data, Encoders.STRING());
		return sparkClusteringService.clusterPredict(sqlc.read().json(df)).toDF().toJSON().first();
	}

	public String trainModel(String persona) {
//		Dataset<Row> message = sqlContext.read().format("kafka").option("kafka.bootstrap.servers", "localhost:9092")
//				.option("subscribe", "users").load();
		String res = "STAGED";
		messageBuffer.add(persona);
		if (messageBuffer.size() == 100) {
			logger.info("Begin training");
			Dataset<String> df = sqlc.createDataset(messageBuffer, Encoders.STRING());
			sparkClusteringService.clusterTrain(sqlc.read().json(df).toDF());
			messageBuffer.clear();
			logger.info("Training finished");
			res="SUCCESS";
		}
		return res;
	}
}