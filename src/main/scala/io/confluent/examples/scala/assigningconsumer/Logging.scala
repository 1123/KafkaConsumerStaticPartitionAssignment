package io.confluent.examples.scala.assigningconsumer

import java.util.logging.Logger

trait Logging {
  lazy val log = Logger.getLogger(getClass.getName)
}
