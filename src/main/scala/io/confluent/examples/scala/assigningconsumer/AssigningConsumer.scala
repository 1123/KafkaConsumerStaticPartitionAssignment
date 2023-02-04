package io.confluent.examples.scala.assigningconsumer

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
import scala.collection.JavaConverters.*
import scala.util.Using

object AssigningConsumer extends Logging {

  def main(args: Array[String]) = {
    log.info("Starting up");
    val properties = new Properties
    val stream = Thread.currentThread.getContextClassLoader.getResourceAsStream("consumer.properties")
    properties.load(stream)
    Using(new KafkaConsumer[String, String](properties)) {
      consumer => {
        log.info("Before assigning partitions")
        val kafkaPartitions = topicPartitions(partitions()).asJava
        consumer.assign(kafkaPartitions)
        while (true) {
          val records = consumer.poll(Duration.ofSeconds(5))
          records.forEach(r => log.info("Received: " + r.value()))
        }
      }
    }
    log.info("Shutting down")
  }

  def topicPartitions(partitions: List[Partition]): List[TopicPartition] = {
    log.info("Mapping partitions to Kafka Topic Partitions class")
    partitions.map(p => new TopicPartition(p.topic, p.partition))
  }

  def partitions(): List[Partition] = {
    log.info("Reading assigned partitions from the environment. ")
    if (System.getenv("ASSIGNED_PARTITIONS") == null || System.getenv("ASSIGNED_PARTITIONS").equals("")) {
      log.severe("Environment variable ASSIGNED_PARTITIONS must be set")
      throw new RuntimeException("Environment variable ASSIGNED_PARTITIONS must be set")
    }
    parsePartitions(System.getenv("ASSIGNED_PARTITIONS"))
  }

  def parsePartitions(json: String): List[Partition] = {
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(json, new TypeReference[List[Partition]] {})
  }

}



