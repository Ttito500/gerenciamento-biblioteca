resource "google_compute_instance" "docker_instance" {
  name         = var.instance_name
  machine_type = "f1-micro"
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-11"
    }
  }

  network_interface {
    network       = "default"
    access_config {}  # IP público
  }

   metadata = {
    repo_url    = var.repo_url

    db_name          = var.db_name
    db_user          = var.db_user
    db_port          = var.db_port 
    db_schema_name   = var.db_schema_name 
    db_password      = var.db_password

    jdbc_url         = var.jdbc_url
    api_port         = var.api_port

    email_smtp       = var.email_smtp 
    senha_email_smtp = var.senha_email_smtp

    jwt_secret       = var.jwt_secret
  }

  metadata_startup_script = <<-EOF
  set -e

  apt-get update && apt-get install -y docker.io docker-compose git

  systemctl start docker

  export DB_NAME="${db_name}"
  export DB_USER="${db_user}"
  export DB_PORT="${db_port}"
  export JDBC_URL="${jdbc_url}"
  export DB_SCHEMA_NAME="${db_schema_name}"
  export API_PORT="${api_port}"
  export EMAIL_SMTP="${email_smtp}"
  export DB_PASSWORD="${db_password}"
  export SENHA_EMAIL_SMTP="${senha_email_smtp}"
  export JWT_SECRET="${jwt_secret}"

  git clone "${repo_url}" /opt/app
  cd /opt/app

  cd bibliotech/bibliotech

  # Roda apenas o banco e a API
  docker-compose up -d --build postgres api
  EOF
}
