package com.josear33.spring.kafka.spark.service;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparkClusteringService {

	@Autowired
	private KMeans model;

	@Autowired
	private SQLContext sqlc;

	private PipelineModel trainedModel = null;

	private final Logger logger = LoggerFactory.getLogger(SparkClusteringService.class);

	public Dataset<Row> clusterPredict(Dataset<Row> dataset) {
		if (trainedModel == null) {
			logger.info("Model needs training");
			clusterTrain(dataset);
		}
		return predict(dataset);
	}

	private Dataset<Row> predict(Dataset<Row> dataset) {
		logger.info("Clustering data");
		return trainedModel.transform(dataset);
	}

	public void clusterTrain(Dataset<Row> dataset) {
//		dataset = dataset.withColumn("features",functions.array(dataset.col("fiebre")));

//		StringIndexer gindexer = new StringIndexer().setInputCol("ciudad").setOutputCol("ciudadIndex");
//		OneHotEncoderEstimator gencoder = new OneHotEncoderEstimator().setInputCols(new String[] { "ciudadIndex" })
//				.setOutputCols(new String[] { "ciudadVec" });
		logger.info("Begin training");

		VectorAssembler assembler = new VectorAssembler()
				.setInputCols(new String[] { "edad", "fiebre", "covidPositivo", "tos", "mucosidad", "sequedad" })
				.setOutputCol("features");
		Pipeline pipe = new Pipeline().setStages(new PipelineStage[] { assembler, model });
		trainedModel = pipe.fit(dataset.drop("features").drop("prediction").dropDuplicates());
		logger.info("Model trained");

	}
}
