package io.github.config
import io.circe.generic.semiauto._
import io.circe.Decoder

case class SourceConfig(path: String, format: String)
case class TransformationsConfig(filterColumn: String, filterValue:String)
case class AggregationsConfig(groupByColumn: String, aggColumn: String, operation: String)
case class AppConfig(source: SourceConfig, transformations: TransformationsConfig, aggregations: AggregationsConfig)

object AppConfig {
  implicit val sourceConfigDecoder: Decoder[SourceConfig] = deriveDecoder[SourceConfig]
  implicit val transformationsConfigDecoder: Decoder[TransformationsConfig] = deriveDecoder[TransformationsConfig]
  implicit val aggregationsConfigDecoder: Decoder[AggregationsConfig] = deriveDecoder[AggregationsConfig]
  implicit val appConfigDecoder: Decoder[AppConfig] = deriveDecoder[AppConfig]
}
