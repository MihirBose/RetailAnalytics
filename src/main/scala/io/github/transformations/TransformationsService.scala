package io.github.transformations
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import scala.util.{Try, Success, Failure}

object TransformationsService {
  implicit class DataFrameOps(df: DataFrame) {
    def safeFilter(filterColumn: String, filterValue: String): DataFrame = {
      Try(df.filter(df(filterColumn) === filterValue)) match {
        case Success(filteredDF) => filteredDF
        case Failure(exception: Exception) =>
          println(s"Filter operation failed: ${exception.getMessage}")
          df
      }
    }
  }

  def transformer(df: DataFrame, filterColumn: String, filterValue: String): DataFrame = {
    val transformedDF: DataFrame = df.safeFilter(filterColumn, filterValue)
    transformedDF
  }
}
