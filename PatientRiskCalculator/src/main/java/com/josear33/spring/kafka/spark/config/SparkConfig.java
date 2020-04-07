package com.josear33.spring.kafka.spark.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
	@Value("${spark.app.name}")
	private String appName;
	@Value("${spark.master}")
	private String masterUri;

	@Bean
	public SparkConf conf() {
		return new SparkConf().setAppName(appName).setMaster(masterUri);
	}

	@Bean
	public JavaSparkContext sc() {
		return new JavaSparkContext(conf());
	}
	
	@Bean
	public JavaStreamingContext jStreamContext() {
		return new JavaStreamingContext(sc(), new Duration(1000));
	}

	public String getAppName() {
		return appName;
	}

	public SparkConfig setAppName(String appName) {
		this.appName = appName;
		return this;
	}

	public String getMasterUri() {
		return masterUri;
	}

	public SparkConfig setMasterUri(String masterUri) {
		this.masterUri = masterUri;
		return this;
	}
}
