package modules

import com.google.inject.AbstractModule
import controllers.{CustomAuthorizer, DemoHttpActionAdapter}
import org.pac4j.cas.client.CasClient
import org.pac4j.cas.client.CasClient.CasProtocol
import org.pac4j.core.authorization.RequireAnyRoleAuthorizer
import org.pac4j.core.client.Clients
import org.pac4j.http.client.direct.{DirectBasicAuthClient, ParameterClient}
import org.pac4j.http.client.indirect.{FormClient, IndirectBasicAuthClient}
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator
import org.pac4j.oauth.client.{TwitterClient, FacebookClient}
import org.pac4j.oidc.client.OidcClient
import org.pac4j.play.cas.logout.PlayCacheLogoutHandler
import org.pac4j.play.http.HttpActionAdapter
import org.pac4j.play.store.{PlayCacheStore, DataStore}
import org.pac4j.play.{ApplicationLogoutController, CallbackController}
import org.pac4j.saml.client.SAML2ClientConfiguration
import play.api.{ Configuration, Environment }
import java.io.File
import org.pac4j.core.config.Config
import org.pac4j.saml.client.SAML2Client

/**
 * Guice DI module to be included in application.conf
 */
class SecurityModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure(): Unit = {

    val fbId = configuration.getString("fbId").get
    val fbSecret = configuration.getString("fbSecret").get
    val baseUrl = configuration.getString("baseUrl").get
    
    // OAuth
    val facebookClient = new FacebookClient(fbId, fbSecret)
    facebookClient.setFields("id,email")
    facebookClient.setScope("email")
    
    
     // OpenID Connect
    val oidcClient = new OidcClient()
    val googleId = configuration.getString("googleId").get
    val googleSecret = configuration.getString("googleSecret").get
    oidcClient.setClientID(googleId)
    oidcClient.setSecret(googleSecret)
    oidcClient.setDiscoveryURI("https://accounts.google.com/.well-known/openid-configuration")
    oidcClient.addCustomParam("prompt", "consent")
    
    
    
    // HTTP
    val formClient = new FormClient(baseUrl + "/reg", new LPTUsernamePasswordAuthenticator())
    val indirectBasicAuthClient = new IndirectBasicAuthClient(new SimpleTestUsernamePasswordAuthenticator())

    val playCacheStore = new PlayCacheStore()
        // for test purposes: profile timeout = 60 seconds
    //cacheStore.setProfileTimeout(60)
    bind(classOf[DataStore]).toInstance(playCacheStore) 

   
    // basic auth
    val directBasicAuthClient = new DirectBasicAuthClient(new SimpleTestUsernamePasswordAuthenticator)

    val clients = new Clients(baseUrl + "/callback", facebookClient,  formClient,
      indirectBasicAuthClient, directBasicAuthClient,oidcClient)

    val config = new Config(clients)
    config.addAuthorizer("admin", new RequireAnyRoleAuthorizer("ROLE_ADMIN"))
    config.addAuthorizer("custom", new CustomAuthorizer)
    
    bind(classOf[Config]).toInstance(config)

    // extra HTTP action handler
    bind(classOf[HttpActionAdapter]).to(classOf[DemoHttpActionAdapter])

    // callback
    val callbackController = new CallbackController()
    callbackController.setDefaultUrl("/")
    bind(classOf[CallbackController]).toInstance(callbackController)

    // logout
    val logoutController = new ApplicationLogoutController()
    logoutController.setDefaultUrl("/")
    bind(classOf[ApplicationLogoutController]).toInstance(logoutController)
  }
}
