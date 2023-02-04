terraform {
  required_providers {
    confluent = {
      source  = "confluentinc/confluent"
      version = "1.28.0"
    }
  }
}

provider "confluent" {
  cloud_api_key    = var.confluent_cloud_api_key
  cloud_api_secret = var.confluent_cloud_api_secret
}

resource "confluent_environment" "benedikt-tf" {
   display_name = "benedikt-terraform"
}

resource "confluent_kafka_cluster" "basic" {
  display_name = "assiging-consumer-test"
  availability = "SINGLE_ZONE"
  cloud        = "AWS"
  region       = "us-east-2"
  basic {}
  environment {
    id = confluent_environment.benedikt-tf.id
  }
}

resource "confluent_service_account" "assigning-consumer-manager" {
  display_name = "assigning-consumer-manager"
  description  = "Service account to manage 'assigning-consumer-test' Kafka cluster"
}

resource "confluent_role_binding" "assigning-consumer-manager-kafka-cluster-admin" {
  principal   = "User:${confluent_service_account.assigning-consumer-manager.id}"
  role_name   = "CloudClusterAdmin"
  crn_pattern = confluent_kafka_cluster.basic.rbac_crn
}

resource "confluent_api_key" "assigning-consumer-manager-kafka-api-key" {
  display_name = "assigning-consumer-manager-kafka-api-key"
  description  = "Kafka API Key that is owned by 'assigning-consumer-manager' service account"
  owner {
    id          = confluent_service_account.assigning-consumer-manager.id
    api_version = confluent_service_account.assigning-consumer-manager.api_version
    kind        = confluent_service_account.assigning-consumer-manager.kind
  }

  managed_resource {
    id          = confluent_kafka_cluster.basic.id
    api_version = confluent_kafka_cluster.basic.api_version
    kind        = confluent_kafka_cluster.basic.kind

    environment {
      id = confluent_environment.benedikt-tf.id
    }
  }

  depends_on = [
    confluent_role_binding.assigning-consumer-manager-kafka-cluster-admin
  ]
}

resource "confluent_kafka_topic" "track-events" {
  kafka_cluster {
    id = confluent_kafka_cluster.basic.id
  }
  topic_name    = "track-events"
  rest_endpoint = confluent_kafka_cluster.basic.rest_endpoint
  credentials {
    key    = confluent_api_key.assigning-consumer-manager-kafka-api-key.id
    secret = confluent_api_key.assigning-consumer-manager-kafka-api-key.secret
  }
}

resource "confluent_kafka_topic" "deliveries" {
  kafka_cluster {
    id = confluent_kafka_cluster.basic.id
  }
  topic_name    = "deliveries"
  rest_endpoint = confluent_kafka_cluster.basic.rest_endpoint
  credentials {
    key    = confluent_api_key.assigning-consumer-manager-kafka-api-key.id
    secret = confluent_api_key.assigning-consumer-manager-kafka-api-key.secret
  }
}

resource "confluent_kafka_topic" "orders" {
  kafka_cluster {
    id = confluent_kafka_cluster.basic.id
  }
  topic_name    = "orders"
  rest_endpoint = confluent_kafka_cluster.basic.rest_endpoint
  credentials {
    key    = confluent_api_key.assigning-consumer-manager-kafka-api-key.id
    secret = confluent_api_key.assigning-consumer-manager-kafka-api-key.secret
  }
}
