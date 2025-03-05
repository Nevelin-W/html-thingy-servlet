provider "aws" {
  region = var.region
}

# S3 Bucket for Deployment
resource "aws_s3_bucket" "deployment_bucket" {
  bucket = "servlet-webapp-deployment-${random_string.bucket_suffix.result}"
  force_destroy = true
}

# Random string to ensure unique bucket name
resource "random_string" "bucket_suffix" {
  length  = 8
  special = false
  upper   = false
}

# S3 Bucket ACL and Public Access Block
resource "aws_s3_bucket_public_access_block" "deployment_bucket_access" {
  bucket = aws_s3_bucket.deployment_bucket.id

  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
}

# S3 Bucket Versioning
resource "aws_s3_bucket_versioning" "deployment_bucket_versioning" {
  bucket = aws_s3_bucket.deployment_bucket.id
  versioning_configuration {
    status = "Enabled"
  }
}

# Elastic Beanstalk Application
resource "aws_elastic_beanstalk_application" "webapp" {
  name        = "servlet-webapp"
  description = "Servlet Web Application"
}

# Elastic Beanstalk Environment
resource "aws_elastic_beanstalk_environment" "webapp_env" {
  name                = "servlet-webapp-env"
  application         = aws_elastic_beanstalk_application.webapp.name
  solution_stack_name = "64bit Amazon Linux 2 v4.7.7 running Tomcat 9 Corretto 11"

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "InstanceType"
    value     = "t3.micro"
  }
}


# Outputs
output "deployment_bucket_name" {
  value = aws_s3_bucket.deployment_bucket.id
}

output "environment_url" {
  value = aws_elastic_beanstalk_environment.webapp_env.endpoint_url
}
