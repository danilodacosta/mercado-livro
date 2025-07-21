resource "aws_ecr_repository" "app" {
  name = "mercado-livro-api"
  image_scanning_configuration {
    scan_on_push = true
  }
  tags = {
    Name = "mercado-livro-api"
  }
}