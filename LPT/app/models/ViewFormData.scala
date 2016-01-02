package models



case class RegistrationData(email: String, password: String, passwordAgain: String)
case class LoginData(email:String, password:String)