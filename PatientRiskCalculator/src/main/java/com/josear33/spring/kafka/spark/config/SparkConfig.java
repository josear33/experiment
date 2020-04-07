package com.josear33.spring.kafka.spark.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
	@Value("${spark.app.name}")
	private String appName;
	@Value("${spark.master}")
	private String masterUri;
	@Value("${spark.driver.port}")
	private Integer driverPort;
	@Value("${spark.deploy.mode}")
	private String deployMode;
	@Value("${spark.executor.memory}")
	private String executorMem;
	@Value("${spark.serializer}")
	private String kryoSerializer;

	@Bean
	public SparkConf conf() {
		return new SparkConf().setAppName(appName).setMaster(masterUri).set("spark.deploy.mode", deployMode)
				.set("spark.driver.port", driverPort.toString()).set("spark.executor.memory", executorMem)
				.set("spark.serializer", kryoSerializer);
	}

	@Bean
	public JavaSparkContext sc() {
		return new JavaSparkContext(conf());
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
