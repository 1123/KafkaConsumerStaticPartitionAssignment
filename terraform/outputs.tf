output "resource-ids" {
  value = <<-EOT
  Environment ID:   ${confluent_environment.benedikt-tf.id} 
  EOT
}

output "kafka-cluster" {
  description = "Bootstrap Servers of Kafka Cluster"
  value = confluent_kafka_cluster.basic.bootstrap_endpoint
}

output "api-key" {
  description = "API Key for the assigning consumer app"
  value       = confluent_api_key.assigning-consumer-manager-kafka-api-key.id
}

output "api-secret" {
  description = "API Secret for the assigning consumer app"
  sensitive = true
  value       = confluent_api_key.assigning-consumer-manager-kafka-api-key.secret
}
