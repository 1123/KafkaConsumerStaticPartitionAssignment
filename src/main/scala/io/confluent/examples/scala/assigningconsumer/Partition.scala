package io.confluent.examples.scala.assigningconsumer

import scala.beans.BeanProperty

case class Partition(
                      @BeanProperty topic: String,
                      @BeanProperty partition: Int
                    )
