package awesome.connector

import dataquality.common.config.{InternalConfig, QualityCheckConfig}
import dataquality.common.connectors.BatchDQConnector
import dataquality.common.model.{Connection, QualityCheck}
import org.apache.spark.sql.{DataFrame, SparkSession}

class AvroConnector extends BatchDQConnector {


  override def runQueryV2(qualityCheck: QualityCheck, sourceConn: Connection, spark: SparkSession): DataFrame = {

    val sourceDF: DataFrame = read(sourceConn.connOptions.get("file_path").get, spark)

    getQueryResult(sourceDF, qualityCheck.query, sourceConn.connOptions.get("file_table_name").get, spark)

  }


  private def read(path: String, spark: SparkSession): DataFrame = {
    spark.read.format("avro").load(path)
  }

  private def getQueryResult(sourceDF: DataFrame, query: String, tableName: String, spark: SparkSession): DataFrame = {
    sourceDF.createTempView(tableName)
    val result = spark.sql(query)
    spark.catalog.dropTempView(tableName)
    result
  }

  override def runQuery(qualityConfig: QualityCheckConfig, internalConfig: Map[String, InternalConfig], spark: SparkSession): DataFrame = ???
}


