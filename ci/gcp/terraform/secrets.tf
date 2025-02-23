resource "google_secret_manager_secret" "db_password" {
  secret_id = "db-password"
  replication {
    auto {}
  }
}

resource "google_secret_manager_secret_version" "db_password_version" {
  secret      = google_secret_manager_secret.db_password.id
  secret_data = var.db_password
}

data "google_secret_manager_secret_version" "db_password_latest" {
  secret  = google_secret_manager_secret.db_password.id
  version = "latest"
}




resource "google_secret_manager_secret" "senha_email_smtp" {
  secret_id = "senha-email-smtp"
  replication {
    auto {}
  }
}

resource "google_secret_manager_secret_version" "senha_email_smtp_version" {
  secret      = google_secret_manager_secret.senha_email_smtp.id
  secret_data = var.senha_email_smtp
}

data "google_secret_manager_secret_version" "senha_email_smtp_latest" {
  secret  = google_secret_manager_secret.senha_email_smtp.id
  version = "latest"
}




resource "google_secret_manager_secret" "jwt_secret" {
  secret_id = "jwt-secret"
  replication {
    auto {}
  }
}

resource "google_secret_manager_secret_version" "jwt_secret_version" {
  secret      = google_secret_manager_secret.jwt_secret.id
  secret_data = var.jwt_secret
}

data "google_secret_manager_secret_version" "jwt_secret_latest" {
  secret  = google_secret_manager_secret.jwt_secret.id
  version = "latest"
}