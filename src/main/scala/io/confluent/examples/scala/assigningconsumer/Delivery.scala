package io.confluent.examples.scala.assigningconsumer

import scala.beans.BeanProperty

case class Delivery(
                     @BeanProperty deliveryId: Int,
                     @BeanProperty deliveryTime: Long,
                     @BeanProperty orderIt: Int,
                     @BeanProperty address: String
                   ) {}
