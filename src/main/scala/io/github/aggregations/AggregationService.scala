package io.github.aggregations
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object AggregationService {
  def aggregator(df: DataFrame, groupByColumn: String, aggColumn: String, operation: String): DataFrame = {
    operation match {
      case "sum" => df.groupBy(groupByColumn).agg(sum(aggColumn).alias("sum_" + aggColumn))
      case "avg" => df.groupBy(groupByColumn).agg(avg(aggColumn).alias("avg_" + aggColumn))
      case _ => throw new IllegalArgumentException("Invalid aggregation operation")
    }
  }
}
