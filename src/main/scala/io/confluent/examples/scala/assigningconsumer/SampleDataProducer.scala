package io.confluent.examples.scala.assigningconsumer

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.{Properties, Random}
import scala.util.Using

class SampleDataProducer extends Logging {

  def generateMessages[T](topic: String, f: Random => T, delay: Long): Unit = {
    val r = new Random()
    val mapper = new ObjectMapper()
    log.info("Starting up");
    val properties = new Properties
    val stream = Thread.currentThread.getContextClassLoader.getResourceAsStream("producer.properties")
    properties.load(stream)
    Using(new KafkaProducer[String, String](properties)) {
      producer => {
        var i = 0;
        while (true) {
          val value = f(r): T
          log.info("Sending " + mapper.writeValueAsString(value))
          producer.send(
            new ProducerRecord[String, String](
              topic, i + "", mapper.writeValueAsString(value)
            )
          )
          i = i + 1
          Thread.sleep(delay)
        }
      }
    }
    log.info("Shutting down")
  }

}
