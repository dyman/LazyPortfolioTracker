package models.db
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.PostgresDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Accounttype.schema, Country.schema, Currency.schema, PlayEvolutions.schema, Quote.schema, Rate.schema, Registration.schema, User.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Accounttype
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(255,true), Default(None)
   *  @param countryid Database column countryid SqlType(int2) */
  case class AccounttypeRow(id: Int, name: Option[String] = None, countryid: Short)
  /** GetResult implicit for fetching AccounttypeRow objects using plain SQL queries */
  implicit def GetResultAccounttypeRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Short]): GR[AccounttypeRow] = GR{
    prs => import prs._
    AccounttypeRow.tupled((<<[Int], <<?[String], <<[Short]))
  }
  /** Table description of table accounttype. Objects of this class serve as prototypes for rows in queries. */
  class Accounttype(_tableTag: Tag) extends Table[AccounttypeRow](_tableTag, "accounttype") {
    def * = (id, name, countryid) <> (AccounttypeRow.tupled, AccounttypeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, Rep.Some(countryid)).shaped.<>({r=>import r._; _1.map(_=> AccounttypeRow.tupled((_1.get, _2, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column countryid SqlType(int2) */
    val countryid: Rep[Short] = column[Short]("countryid")

    /** Foreign key referencing Country (database name country_fkey) */
    lazy val countryFk = foreignKey("country_fkey", countryid, Country)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Accounttype */
  lazy val Accounttype = new TableQuery(tag => new Accounttype(tag))

  /** Entity class storing rows of table Country
   *  @param id Database column id SqlType(int2), PrimaryKey
   *  @param code Database column code SqlType(varchar), Length(10,true), Default(None)
   *  @param currency Database column currency SqlType(varchar), Length(3,true), Default(None)
   *  @param name Database column name SqlType(varchar), Length(255,true), Default(None) */
  case class CountryRow(id: Short, code: Option[String] = None, currency: Option[String] = None, name: Option[String] = None)
  /** GetResult implicit for fetching CountryRow objects using plain SQL queries */
  implicit def GetResultCountryRow(implicit e0: GR[Short], e1: GR[Option[String]]): GR[CountryRow] = GR{
    prs => import prs._
    CountryRow.tupled((<<[Short], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table country. Objects of this class serve as prototypes for rows in queries. */
  class Country(_tableTag: Tag) extends Table[CountryRow](_tableTag, "country") {
    def * = (id, code, currency, name) <> (CountryRow.tupled, CountryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), code, currency, name).shaped.<>({r=>import r._; _1.map(_=> CountryRow.tupled((_1.get, _2, _3, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int2), PrimaryKey */
    val id: Rep[Short] = column[Short]("id", O.PrimaryKey)
    /** Database column code SqlType(varchar), Length(10,true), Default(None) */
    val code: Rep[Option[String]] = column[Option[String]]("code", O.Length(10,varying=true), O.Default(None))
    /** Database column currency SqlType(varchar), Length(3,true), Default(None) */
    val currency: Rep[Option[String]] = column[Option[String]]("currency", O.Length(3,varying=true), O.Default(None))
    /** Database column name SqlType(varchar), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))

    /** Foreign key referencing Currency (database name currency_id) */
    lazy val currencyFk = foreignKey("currency_id", currency, Currency)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Country */
  lazy val Country = new TableQuery(tag => new Country(tag))

  /** Entity class storing rows of table Currency
   *  @param id Database column id SqlType(varchar), PrimaryKey, Length(3,true)
   *  @param name Database column name SqlType(varchar), Length(255,true), Default(None) */
  case class CurrencyRow(id: String, name: Option[String] = None)
  /** GetResult implicit for fetching CurrencyRow objects using plain SQL queries */
  implicit def GetResultCurrencyRow(implicit e0: GR[String], e1: GR[Option[String]]): GR[CurrencyRow] = GR{
    prs => import prs._
    CurrencyRow.tupled((<<[String], <<?[String]))
  }
  /** Table description of table currency. Objects of this class serve as prototypes for rows in queries. */
  class Currency(_tableTag: Tag) extends Table[CurrencyRow](_tableTag, "currency") {
    def * = (id, name) <> (CurrencyRow.tupled, CurrencyRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name).shaped.<>({r=>import r._; _1.map(_=> CurrencyRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(varchar), PrimaryKey, Length(3,true) */
    val id: Rep[String] = column[String]("id", O.PrimaryKey, O.Length(3,varying=true))
    /** Database column name SqlType(varchar), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Currency */
  lazy val Currency = new TableQuery(tag => new Currency(tag))

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(int4), PrimaryKey
   *  @param hash Database column hash SqlType(varchar), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(timestamp)
   *  @param applyScript Database column apply_script SqlType(text), Default(None)
   *  @param revertScript Database column revert_script SqlType(text), Default(None)
   *  @param state Database column state SqlType(varchar), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(text), Default(None) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends Table[PlayEvolutionsRow](_tableTag, "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(varchar), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(timestamp) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(text), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Default(None))
    /** Database column revert_script SqlType(text), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Default(None))
    /** Database column state SqlType(varchar), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(text), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table Quote
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param quote Database column quote SqlType(text)
   *  @param author Database column author SqlType(varchar), Length(255,true) */
  case class QuoteRow(id: Int, quote: String, author: String)
  /** GetResult implicit for fetching QuoteRow objects using plain SQL queries */
  implicit def GetResultQuoteRow(implicit e0: GR[Int], e1: GR[String]): GR[QuoteRow] = GR{
    prs => import prs._
    QuoteRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table quote. Objects of this class serve as prototypes for rows in queries. */
  class Quote(_tableTag: Tag) extends Table[QuoteRow](_tableTag, "quote") {
    def * = (id, quote, author) <> (QuoteRow.tupled, QuoteRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(quote), Rep.Some(author)).shaped.<>({r=>import r._; _1.map(_=> QuoteRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column quote SqlType(text) */
    val quote: Rep[String] = column[String]("quote")
    /** Database column author SqlType(varchar), Length(255,true) */
    val author: Rep[String] = column[String]("author", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table Quote */
  lazy val Quote = new TableQuery(tag => new Quote(tag))

  /** Entity class storing rows of table Rate
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param currencyid Database column currencyid SqlType(varchar), Length(3,true), Default(None)
   *  @param ondate Database column ondate SqlType(date), Default(None)
   *  @param rate Database column rate SqlType(numeric), Default(None) */
  case class RateRow(id: Int, currencyid: Option[String] = None, ondate: Option[java.sql.Date] = None, rate: Option[scala.math.BigDecimal] = None)
  /** GetResult implicit for fetching RateRow objects using plain SQL queries */
  implicit def GetResultRateRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[java.sql.Date]], e3: GR[Option[scala.math.BigDecimal]]): GR[RateRow] = GR{
    prs => import prs._
    RateRow.tupled((<<[Int], <<?[String], <<?[java.sql.Date], <<?[scala.math.BigDecimal]))
  }
  /** Table description of table rate. Objects of this class serve as prototypes for rows in queries. */
  class Rate(_tableTag: Tag) extends Table[RateRow](_tableTag, "rate") {
    def * = (id, currencyid, ondate, rate) <> (RateRow.tupled, RateRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), currencyid, ondate, rate).shaped.<>({r=>import r._; _1.map(_=> RateRow.tupled((_1.get, _2, _3, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column currencyid SqlType(varchar), Length(3,true), Default(None) */
    val currencyid: Rep[Option[String]] = column[Option[String]]("currencyid", O.Length(3,varying=true), O.Default(None))
    /** Database column ondate SqlType(date), Default(None) */
    val ondate: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("ondate", O.Default(None))
    /** Database column rate SqlType(numeric), Default(None) */
    val rate: Rep[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("rate", O.Default(None))

    /** Foreign key referencing Currency (database name rate_currencyid_fkey) */
    lazy val currencyFk = foreignKey("rate_currencyid_fkey", currencyid, Currency)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Rate */
  lazy val Rate = new TableQuery(tag => new Rate(tag))

  /** Entity class storing rows of table Registration
   *  @param id Database column id SqlType(varchar), PrimaryKey, Length(255,true)
   *  @param email Database column email SqlType(varchar), Length(255,true)
   *  @param password Database column password SqlType(varchar), Length(255,true)
   *  @param ondate Database column ondate SqlType(timestamp)
   *  @param confirmed Database column confirmed SqlType(bool), Default(None) */
  case class RegistrationRow(id: String, email: String, password: String, ondate: java.sql.Timestamp, confirmed: Option[Boolean] = None)
  /** GetResult implicit for fetching RegistrationRow objects using plain SQL queries */
  implicit def GetResultRegistrationRow(implicit e0: GR[String], e1: GR[java.sql.Timestamp], e2: GR[Option[Boolean]]): GR[RegistrationRow] = GR{
    prs => import prs._
    RegistrationRow.tupled((<<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[Boolean]))
  }
  /** Table description of table registration. Objects of this class serve as prototypes for rows in queries. */
  class Registration(_tableTag: Tag) extends Table[RegistrationRow](_tableTag, "registration") {
    def * = (id, email, password, ondate, confirmed) <> (RegistrationRow.tupled, RegistrationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(email), Rep.Some(password), Rep.Some(ondate), confirmed).shaped.<>({r=>import r._; _1.map(_=> RegistrationRow.tupled((_1.get, _2.get, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(varchar), PrimaryKey, Length(255,true) */
    val id: Rep[String] = column[String]("id", O.PrimaryKey, O.Length(255,varying=true))
    /** Database column email SqlType(varchar), Length(255,true) */
    val email: Rep[String] = column[String]("email", O.Length(255,varying=true))
    /** Database column password SqlType(varchar), Length(255,true) */
    val password: Rep[String] = column[String]("password", O.Length(255,varying=true))
    /** Database column ondate SqlType(timestamp) */
    val ondate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("ondate")
    /** Database column confirmed SqlType(bool), Default(None) */
    val confirmed: Rep[Option[Boolean]] = column[Option[Boolean]]("confirmed", O.Default(None))
  }
  /** Collection-like TableQuery object for table Registration */
  lazy val Registration = new TableQuery(tag => new Registration(tag))

  /** Entity class storing rows of table User
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param email Database column email SqlType(varchar), Length(255,true)
   *  @param password Database column password SqlType(varchar), Length(255,true), Default(None)
   *  @param lastactivity Database column lastactivity SqlType(timestamp)
   *  @param isadmin Database column isadmin SqlType(bool), Default(None)
   *  @param lastlogin Database column lastlogin SqlType(varchar), Length(255,true), Default(None) */
  case class UserRow(id: Int, email: String, password: Option[String] = None, lastactivity: java.sql.Timestamp, isadmin: Option[Boolean] = None, lastlogin: Option[String] = None)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[java.sql.Timestamp], e4: GR[Option[Boolean]]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<?[String], <<[java.sql.Timestamp], <<?[Boolean], <<?[String]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends Table[UserRow](_tableTag, "user") {
    def * = (id, email, password, lastactivity, isadmin, lastlogin) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(email), password, Rep.Some(lastactivity), isadmin, lastlogin).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3, _4.get, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column email SqlType(varchar), Length(255,true) */
    val email: Rep[String] = column[String]("email", O.Length(255,varying=true))
    /** Database column password SqlType(varchar), Length(255,true), Default(None) */
    val password: Rep[Option[String]] = column[Option[String]]("password", O.Length(255,varying=true), O.Default(None))
    /** Database column lastactivity SqlType(timestamp) */
    val lastactivity: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("lastactivity")
    /** Database column isadmin SqlType(bool), Default(None) */
    val isadmin: Rep[Option[Boolean]] = column[Option[Boolean]]("isadmin", O.Default(None))
    /** Database column lastlogin SqlType(varchar), Length(255,true), Default(None) */
    val lastlogin: Rep[Option[String]] = column[Option[String]]("lastlogin", O.Length(255,varying=true), O.Default(None))

    /** Uniqueness Index over (email) (database name email_uniqe) */
    val index1 = index("email_uniqe", email, unique=true)
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}
