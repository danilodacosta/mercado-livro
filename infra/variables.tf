variable "aws_region" {
  description = "AWS region"
  default     = "us-east-1"
}

variable "cluster_name" {
  description = "EKS cluster name"
  default     = "mercado-livro-eks"
}

variable "node_instance_type" {
  description = "EC2 instance type (Free Tier)"
  default     = "t3.micro"
}

variable "desired_capacity" {
  description = "Number of EC2 worker nodes"
  default     = 1
}
