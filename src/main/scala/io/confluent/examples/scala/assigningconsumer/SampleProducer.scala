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
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.stream.Collectors
import scala.beans.BeanProperty
import scala.collection.JavaConverters.*
import scala.util.Using

object SampleProducer extends Logging {

  def main(args: Array[String]) = {
    log.info("Starting up");
    val properties = new Properties
    val stream = Thread.currentThread.getContextClassLoader.getResourceAsStream("producer.properties")
    properties.load(stream)
    Using(new KafkaProducer[String, String](properties)) {
      producer => {
        var i = 0;
        while (true) {
          log.info("Sending sample record " + i)
          producer.send(new ProducerRecord[String, String]("numbers", i + "", i + ""))
          i = i + 1
          Thread.sleep(10)
        }
      }
    }
    log.info("Shutting down")
  }

}

