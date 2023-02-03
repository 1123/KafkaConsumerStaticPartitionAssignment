package io.confluent.examples.offsetrest

import com.fasterxml.jackson.core.`type`.TypeReference
import org.apache.kafka.clients.consumer.{ConsumerRebalanceListener, KafkaConsumer}
import org.apache.kafka.common.TopicPartition

import java.io.InputStream
import java.time.Duration
import java.util
import java.util.Properties
import java.util.logging.Logger
import java.util.regex.Pattern
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import java.util.stream.Collectors
import scala.beans.BeanProperty
import scala.collection.JavaConverters._

import scala.util.Using

object AssigningConsumer extends Logging {

  def main(args: Array[String]) = {
    log.info("Starting up");
    val properties = new Properties
    val stream = Thread.currentThread.getContextClassLoader.getResourceAsStream("consumer.properties")
    properties.load(stream)
    Using(new KafkaConsumer[String, String](properties)) {
      consumer => {
        val kafkaPartitions = topicPartitions(partitions()).asJava
        consumer.assign(kafkaPartitions)
        while (true) {
          log.info("polling for records")
          val records = consumer.poll(Duration.ofSeconds(5))
          log.info("received records: " + records.count())
        }
      }
    }
    log.info("Shutting down")
  }

  def topicPartitions(partitions: List[Partition]): List[TopicPartition] = {
    partitions.map(p => new TopicPartition(p.topic, p.partition))
  }

  def partitions(): List[Partition] = {
    if (System.getenv("ASSIGNED_PARTITIONS") == null || System.getenv("ASSIGNED_PARTITIONS").equals(""))
      throw new RuntimeException("Environment variable ASSIGNED_PARTITIONS must be set")
    parsePartitions(System.getenv("ASSIGNED_PARTITIONS"))
  }

  def parsePartitions(json: String): List[Partition] = {
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(json, new TypeReference[List[Partition]] {})
  }

}

case class Partition(
                      @BeanProperty topic: String,
                      @BeanProperty partition: Int
                    )

trait Logging {
  lazy val log = Logger.getLogger(getClass.getName)
}