provider "aws" {
  region = var.region
}

resource "aws_elastic_beanstalk_application" "servlet_app" {
  name        = "servlet-web-application"
  description = "Java Servlet Web Application"
}

resource "aws_elastic_beanstalk_environment" "servlet_env" {
  name                = "servlet-environment"
  application         = aws_elastic_beanstalk_application.servlet_app.name
  solution_stack_name = "64bit Amazon Linux 2 v3.3.5 running Tomcat 8.5 Corretto 11"
  
  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "InstanceType"
    value     = "t3.micro"
  }
}
