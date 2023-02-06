export ASSIGNED_PARTITIONS='[
  {"topic":"orders","partition":0},
  {"topic":"orders","partition":1},
  {"topic":"deliveries","partition":0},
  {"topic":"deliveries","partition":1},
  {"topic":"track-events","partition":0},
  {"topic":"track-events","partition":1},
  {"topic":"track-events","partition":2},
  {"topic":"track-events","partition":3},
  {"topic":"track-events","partition":4},
  {"topic":"track-events","partition":5},
  {"topic":"track-events","partition":6},
  {"topic":"track-events","partition":7},
  {"topic":"track-events","partition":8},
  {"topic":"track-events","partition":9}
]'
mvn exec:java -Dexec.mainClass=io.confluent.examples.scala.assigningconsumer.AssigningConsumer

