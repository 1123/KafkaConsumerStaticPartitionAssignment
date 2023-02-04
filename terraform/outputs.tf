output "resource-ids" {
  value = <<-EOT
  Environment ID:   ${confluent_environment.benedikt-tf.id}
  EOT
}
