package com.josear33.spring.kafka.spark.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
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

	public String getRiskScoring(String persona) {
//		Dataset<Row> message = sqlContext.read().format("kafka").option("kafka.bootstrap.servers", "localhost:9092")
//				.option("subscribe", "users").load();

		List<String> data = new ArrayList<String>();
		data.add(persona);
		Dataset<String> df = sqlc.createDataset(data, Encoders.STRING());
		Dataset<Row> formattedDs = sqlc.read().json(df);
		sparkClusteringService.clusterPredict(formattedDs).write().json("tempRow");
		return sqlc.read().json("tempRow").toDF().as(Encoders.STRING()).first();
//		return 60 + (long) (Math.random() * (60 - 100));
	}
}