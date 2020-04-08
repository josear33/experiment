package com.josear33.spring.kafka.spark.service;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.feature.OneHotEncoder;
import org.apache.spark.ml.feature.OneHotEncoderEstimator;
import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparkClusteringService {

	@Autowired
	private SQLContext sqlc;
	@Autowired
	private KMeans model;

	public Dataset<Row> clusterPredict(Dataset<Row> dataset) {
//		dataset = dataset.withColumn("features",functions.array(dataset.col("fiebre")));
//
//		StringIndexer gindexer = new StringIndexer().setInputCol("ciudad").setOutputCol("ciudadIndex");
//		OneHotEncoderEstimator gencoder = new OneHotEncoderEstimator().setInputCols(new String[] { "ciudadIndex" })
//				.setOutputCols(new String[] { "ciudadVec" });

		VectorAssembler assembler = new VectorAssembler()
				.setInputCols(new String[] { "edad", "fiebre", "covidPositivo", "tos", "mucosidad", "sequedad" })
				.setOutputCol("features");
		Pipeline pipe = new Pipeline().setStages(new PipelineStage[] { assembler, model });
		PipelineModel modelFit = pipe.fit(dataset);
		Dataset res = modelFit.transform(dataset);
		return res;
	}

	public void clusterTrain(Dataset<Row> dataset) {
//		dataset = dataset.withColumn("features",functions.array(dataset.col("fiebre")));

		StringIndexer gindexer = new StringIndexer().setInputCol("ciudad").setOutputCol("ciudadIndex");
		OneHotEncoderEstimator gencoder = new OneHotEncoderEstimator().setInputCols(new String[] { "ciudadIndex" })
				.setOutputCols(new String[] { "ciudadVec" });

		VectorAssembler assembler = new VectorAssembler()
				.setInputCols(
						new String[] { "ciudadVec", "edad", "fiebre", "covidPositivo", "tos", "mucosidad", "sequedad" })
				.setOutputCol("features");
		Pipeline pipe = new Pipeline().setStages(new PipelineStage[] { assembler, model });
		PipelineModel modelFit = pipe.fit(dataset);
		// return modelFit.transform(dataset);
	}
}
