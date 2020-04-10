package com.josear33.spring.kafka.spark.config;

import org.apache.spark.SparkConf;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
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
	@Value("${spark.submit.deployMode}")
	private String deployMode;
	@Value("${spark.executor.memory}")
	private String executorMem;
	@Value("${spark.serializer}")
	private String kryoSerializer;
	@Value("${spark.driver.host}")
	private String driverHost;
	@Value("${spark.driver.bindAddress}")
	private String bindAddress;

	@Bean
	public SparkConf conf() {
		return new SparkConf().setAppName(appName).setMaster(masterUri).set("spark.submit.deployMode", deployMode)
				.set("spark.driver.port", driverPort.toString()).set("spark.executor.memory", executorMem)
				.set("spark.serializer", kryoSerializer)//.set("spark.driver.host", driverHost)
				.set("spark.driver.bindAddress", bindAddress);
	}
	
	@Bean
	public KMeans model() {
		return new KMeans().setK(2).setSeed(1L);
	}

	@Bean
	public KMeansModel trainedModel() {
		return KMeansModel.load("");
	}
	
	@Bean
	public SQLContext sqlc() {
		return new SQLContext(SparkSession.builder().config(conf()).getOrCreate());
	}


	public String getAppName() {
		return appName;
	}

	public String getMasterUri() {
		return masterUri;
	}

	public Integer getDriverPort() {
		return driverPort;
	}

	public String getDeployMode() {
		return deployMode;
	}

	public String getExecutorMem() {
		return executorMem;
	}

	public String getKryoSerializer() {
		return kryoSerializer;
	}

	public String getDriverHost() {
		return driverHost;
	}

}