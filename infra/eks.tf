resource "aws_eks_cluster" "eks" {
  name     = var.cluster_name
  role_arn = aws_iam_role.eks_cluster_role.arn

  vpc_config {
    subnet_ids = module.vpc.private_subnets
  }
}

resource "aws_eks_node_group" "node_group" {
  cluster_name    = aws_eks_cluster.eks.name
  node_group_name = "${var.cluster_name}-nodes"
  node_role_arn   = aws_iam_role.eks_node_role.arn
  subnet_ids      = module.vpc.private_subnets

  scaling_config {
    desired_size = var.desired_capacity
    max_size     = var.desired_capacity
    min_size     = 1
  }

  instance_types = [var.node_instance_type]
  ami_type = "AL2023_x86_64_STANDARD"
  capacity_type  = "ON_DEMAND"

  depends_on = [aws_eks_cluster.eks]
}