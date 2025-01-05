package io.github.main

import org.apache.spark.sql.SparkSession
import io.github.config.{AppConfig, ConfigLoader}
import io.github.aggregations.AggregationService
import io.github.transformations.TransformationsService

object Main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Spark Transformation Project")
      .master("local[*]")
      .getOrCreate()

    val configPath = "src/main/config/config.json"
    val config = ConfigLoader.loadConfig[AppConfig](configPath)

    val data = spark.read
            .option("header", "true")
            .csv(config.source.path)

    val transformedData = TransformationsService.transformer(data, config.transformations.filterColumn,
      config.transformations.filterValue)
    val aggregatedData = AggregationService.aggregator(transformedData, config.aggregations.groupByColumn,
      config.aggregations.aggColumn, config.aggregations.operation
    )

    aggregatedData.show()

    spark.stop()
  }
}