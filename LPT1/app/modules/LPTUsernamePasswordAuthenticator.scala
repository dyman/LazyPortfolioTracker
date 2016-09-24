package modules

import org.pac4j.http.credentials.authenticator.UsernamePasswordAuthenticator
import org.pac4j.core.exception.CredentialsException
import org.pac4j.http.credentials.UsernamePasswordCredentials
import org.pac4j.http.profile.HttpProfile
import org.pac4j.core.util.CommonHelper
import org.pac4j.core.profile.CommonProfile
import play.Logger

class LPTUsernamePasswordAuthenticator extends UsernamePasswordAuthenticator {
  def validate(credentials: UsernamePasswordCredentials): Unit = {
    if (credentials == null) {
      throw new CredentialsException("no Credential")
    }
    val username = credentials.getUsername();
    val password = credentials.getPassword();
    Logger.debug("username: " + username + " password: " + password + " wants to be form authenticated")
    if (CommonHelper.isBlank(username)) {
      throw new CredentialsException("Username cannot be blank")
    }
    if (CommonHelper.isBlank(password)) {
      throw new CredentialsException("Password cannot be blank")
    }
    if (CommonHelper.areNotEquals(username, password)) {
      throw new CredentialsException("Username : '" + username + "' does not match password")

    }
    val profile = new HttpProfile();
    profile.setId(username);
    profile.addAttribute("email", username)
    profile.addAttribute(CommonProfile.USERNAME, username);
    credentials.setUserProfile(profile);
  }
}