export ASSIGNED_PARTITIONS='[
  {"topic":"orders","partition":2},
  {"topic":"orders","partition":3},
  {"topic":"deliveries","partition":2},
  {"topic":"deliveries","partition":3},
  {"topic":"track-events","partition":10},
  {"topic":"track-events","partition":11},
  {"topic":"track-events","partition":12},
  {"topic":"track-events","partition":13},
  {"topic":"track-events","partition":14},
  {"topic":"track-events","partition":15},
  {"topic":"track-events","partition":16},
  {"topic":"track-events","partition":17},
  {"topic":"track-events","partition":18},
  {"topic":"track-events","partition":19}
]'
mvn exec:java -Dexec.mainClass=io.confluent.examples.scala.assigningconsumer.AssigningConsumer

