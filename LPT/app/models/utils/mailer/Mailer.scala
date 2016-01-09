package utils.mailer

import models.Message
import play.api.libs.mailer.Email
import play.api.libs.mailer.AttachmentFile
import java.io.File
import org.apache.commons.mail.EmailAttachment
import play.api.Play.current
import javax.inject.Inject
import play.api.libs.mailer._

class Mailer @Inject() (mailer: MailerClient){

  def sendMail(message: Message): Email = {    
    val body = views.html.emails.registeremail.render(message).body
    val email = Email(
      message.subject,
      "lazyportfoliotracker4@gmail.com",
      Seq(message.email),
      bodyText = Some("message"),
      bodyHtml = Some(body),
      bcc = Seq("mandypeter+lpt@gmail.com"),
      attachments = Seq(
        AttachmentFile("favicon.png", new File(current.classloader.getResource("public/images/favicon.png").getPath), contentId = Some(message.cid))))
    mailer.send(email)
    email
  }
}