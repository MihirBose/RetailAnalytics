package io.github.config
import io.circe.parser._
import io.circe.Decoder
import scala.io.Source

object ConfigLoader {
  def loadConfig[T: Decoder](configPath: String): T = {
    val configSource = Source.fromFile(configPath)
    val configContent = try configSource.mkString finally configSource.close()
    decode[T](configContent) match {
      case Right(cfg) => cfg
      case Left(error) => throw new RuntimeException(s"Failed to decode config: ${error.getMessage}")
    }
  }
}
