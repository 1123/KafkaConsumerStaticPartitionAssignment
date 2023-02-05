package io.confluent.examples.scala.assigningconsumer

import org.apache.kafka.clients.consumer.{ConsumerRebalanceListener, KafkaConsumer}
import org.apache.kafka.common.TopicPartition

import java.io.InputStream
import java.time.Duration
import java.util
import java.util.{Properties, Random}
import java.util.logging.Logger
import java.util.regex.Pattern
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import io.confluent.examples.scala.assigningconsumer.DeliveryProducer.log
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.stream.Collectors
import scala.beans.BeanProperty
import scala.collection.JavaConverters.*
import scala.util.Using

object DeliveryProducer extends Logging {

  def main(args: Array[String]) = {
    new SampleDataProducer().generateMessages(
      "deliveries",
      (r: Random) => Delivery(
        r.nextInt(), System.currentTimeMillis(), r.nextInt(), "foo-road 77, bar-city, baz-country"
      ),
      100L
    )
  }

}


