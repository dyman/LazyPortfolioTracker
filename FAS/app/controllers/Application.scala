package controllers

import play.api._
import play.api.mvc._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import javax.inject.Inject
import scala.concurrent.Future
import models.db.Tables.User
import slick.lifted.TableQuery
import slick.driver.PostgresDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {

  def index = Action {
    val db = Database.forConfig("mydb")
    val users = for (u <- User) yield u.email
    val f: Future[Seq[String]] = db.run(users.result)

    Logger.logger.debug("alma")
    f.onSuccess {
      case valami => {
        Logger.debug("answer")
        valami.foreach {
          x => Logger.logger.debug(x)
          Ok(views.html.index(x))
        }
      }

    }

    Ok(views.html.index("ok"))

  }

  //  def user(name: String) = Action {
  //    val users = TableQuery[User]
  //    //val t = users.filter(_.email == name)
  //    //(_.fullname===name)).map(_.email)
  //    Ok(views.html.user("alma"))
  //
  //  }

}
