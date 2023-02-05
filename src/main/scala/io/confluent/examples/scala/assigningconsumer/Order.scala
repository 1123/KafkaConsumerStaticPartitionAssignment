package io.confluent.examples.scala.assigningconsumer

import scala.beans.BeanProperty

case class Order(
                  @BeanProperty orderId: Int,
                  @BeanProperty orderTime: Long,
                  @BeanProperty productId: Int
                ) {}
