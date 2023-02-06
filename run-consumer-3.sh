export ASSIGNED_PARTITIONS='[
  {"topic":"orders","partition":4},
  {"topic":"orders","partition":5},
  {"topic":"deliveries","partition":4},
  {"topic":"deliveries","partition":5},
  {"topic":"track-events","partition":20},
  {"topic":"track-events","partition":21},
  {"topic":"track-events","partition":22},
  {"topic":"track-events","partition":23},
  {"topic":"track-events","partition":24},
  {"topic":"track-events","partition":25},
  {"topic":"track-events","partition":26},
  {"topic":"track-events","partition":27},
  {"topic":"track-events","partition":28},
  {"topic":"track-events","partition":29}
]'
mvn exec:java -Dexec.mainClass=io.confluent.examples.scala.assigningconsumer.AssigningConsumer

