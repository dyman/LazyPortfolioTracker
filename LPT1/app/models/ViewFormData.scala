package models

case class RegistrationData(email: String, password: String, passwordAgain: String)
case class LoginData(email: String, password: String)

case class Message(email: String, subject: String, name: String, code: String, cid: String = "1234") 
object Message{
  def registration(email: String, code: String) = {
    Message(email, "Registration email", "user", code)
  }
}