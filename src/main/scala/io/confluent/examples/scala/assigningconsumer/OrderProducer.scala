package io.confluent.examples.scala.assigningconsumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.kafka.clients.consumer.{ConsumerRebalanceListener, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.TopicPartition

import java.io.InputStream
import java.time.Duration
import java.util
import java.util.{Properties, Random}
import java.util.logging.Logger
import java.util.regex.Pattern
import java.util.stream.Collectors
import scala.beans.BeanProperty
import scala.collection.JavaConverters.*
import scala.util.Using

object OrderProducer extends Logging {

  def main(args: Array[String]) = {
    new SampleDataProducer().generateMessages(
      "orders",
      (r: Random) => Order(r.nextInt(), System.currentTimeMillis(), r.nextInt()),
      100L
    )
  }

}

