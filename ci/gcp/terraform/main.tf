module "instance" {
  source           = "./modules/instance"
  instance_name    = var.instance_name
  zone             = var.zone
  repo_url         = var.repo_url

  db_name          = var.db_name
  db_user          = var.db_user
  db_port          = var.db_port 
  db_schema_name   = var.db_schema_name 
  db_password      = data.google_secret_manager_secret_version.db_password_latest.secret_data

  jdbc_url         = var.jdbc_url
  api_port         = var.api_port

  email_smtp       = var.email_smtp 
  senha_email_smtp = data.google_secret_manager_secret_version.senha_email_smtp_latest.secret_data

  jwt_secret       = data.google_secret_manager_secret_version.jwt_secret_latest.secret_data
}
