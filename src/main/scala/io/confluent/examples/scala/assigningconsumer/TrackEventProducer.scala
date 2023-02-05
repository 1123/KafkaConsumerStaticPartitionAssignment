package io.confluent.examples.scala.assigningconsumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.kafka.clients.consumer.{ConsumerRebalanceListener, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.TopicPartition

import java.beans.BeanProperty
import java.io.InputStream
import java.time.Duration
import java.util
import java.util.logging.Logger
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.{Properties, Random}
import scala.collection.JavaConverters.*
import scala.util.Using

object TrackEventProducer extends Logging {

  def main(args: Array[String]) = {
    new SampleDataProducer().generateMessages(
      "track-events",
      (r: Random) => TrackEvent(r.nextInt(), System.currentTimeMillis(), r.nextInt(), r.nextFloat(), r.nextFloat()),
      10L
    )
  }

}

