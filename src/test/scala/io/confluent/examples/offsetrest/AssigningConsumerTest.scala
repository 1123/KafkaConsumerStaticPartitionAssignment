package io.confluent.examples.offsetrest

import org.apache.kafka.common.TopicPartition
import com.fasterxml.jackson.core
import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import collection.mutable.Stack
import org.scalatest.flatspec.AnyFlatSpec

import scala.beans.BeanProperty
import java.util.Arrays;


class AssigningConsumerTest extends AnyFlatSpec {

  "the assiging consumer" should "parse paritions from JSON String" in {
    val json = """[{"topic":"foo","partition":0},{"topic":"bar","partition":1}]"""
    val parsedPartitions = AssigningConsumer.parsePartitions(json)
    println(parsedPartitions)
    val topicPartitions = AssigningConsumer.topicPartitions(parsedPartitions)
    println(topicPartitions)
  }



}