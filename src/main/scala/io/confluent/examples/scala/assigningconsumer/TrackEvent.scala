package io.confluent.examples.scala.assigningconsumer

import java.beans.BeanProperty

case class TrackEvent(
                       @BeanProperty eventId: Int,
                       @BeanProperty timestamp: Long,
                       @BeanProperty deliveryId: Int,
                       @BeanProperty lat: Float,
                       @BeanProperty long: Float
                     )
