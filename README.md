## Kafka Consumer Manual Partition Assignment Example

In most cases, Kafka consumer applications make use of consumer groups, automatic
partition assignment and rebalancing to achieve horizontal scalability and high availability. 
This allows one instance of a consumer group to take over the work of another instance of the same consumer group in case that instance fails. 
It also allows to scale up or down instances in case the current number of instances is 
less than or more than needed. 
Finally, it allows to automatically react to a changing number of partitions of a topic. 

However, in some cases automatic partition assignment using consumer groups does not 
give enough control over the consumer application. 
This may be the case when the throughput over different partitions or different topics varies, and one wishes to group partitions with high throughput together with parittions of lower throughput, such that sum of throughput over the assigned partitions of a consumer instance is even across consumer instances. 

This can be achieved using manual partition assignment using the Kafka consumer assign API. This repository shows an example of how to do this using Scala. Since the Scala code is using the Kafka Java client libraries, this can be done in a very simlar fashion also with Java. 

### Overview Diagram

The following diagram gives an overview over the demo:

![Static Partition Assignment with the Kafka Consumer Assign API](KafkaConsumerStaticPartitionAssignment.png)

### Prerequisites and Running the Demo

This demo runs with Confluent Cloud, but it can also run against any other Kafka Cluster by adjusting the connection configuration in the `src/main/resources/` folder. 

#### Prerequisites: 
* A Confluent Cloud Account
* Terrafrom for provisioning the Kafka Cluster, API-Keys and the topics
* JDK 11 or higher for compilation and execution
* A recent version of Apache Maven

#### Running the Demo:

* Execute terrafrom in the terrafrom subfolder: `terraform apply`. This will require you to log into your Confluent Cloud account and set up the required resources. 
* Take note of the generated API Key and the bootstrap servers endpoint. 
* Use this information to create files  `src/main/resources/producer.properties` and `src/main/resources/consumer.properties` from the corresponding template files in the same directory. 
* Run the DeliveryProducer, OrderProducer and TrackEventProducer main classes to populate the created topics. 
* Run three instances of the consumer application by executing the scripts `run-consumer-{1,2,3}.sh`; each in a separate terminal window. 
  The consumers will read their parition assignments from the environement variable `ASSIGNED_PARTITIONS` and consume from exactly those partitions. 
  You can manually reassign partitions to different consumer instances by modifiying this variable for each consumer and restart the consumers. 
  Offsets are stored in the `__consumer_offsets` topic just as for ordinary consumers. 







