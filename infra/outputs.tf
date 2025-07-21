output "cluster_endpoint" {
  value = aws_eks_cluster.eks.endpoint
}

output "cluster_name" {
  value = aws_eks_cluster.eks.name
}

output "ecr_repository_url" {
  value = aws_ecr_repository.app.repository_url
}