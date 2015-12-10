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
  lazy val schema: profile.SchemaDescription = Accounttypes.schema ++ Country.schema ++ PlayEvolutions.schema ++ Quotes.schema ++ User.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Accounttypes
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(255,true), Default(None)
   *  @param countryid Database column countryId SqlType(int2) */
  case class AccounttypesRow(id: Int, name: Option[String] = None, countryid: Short)
  /** GetResult implicit for fetching AccounttypesRow objects using plain SQL queries */
  implicit def GetResultAccounttypesRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Short]): GR[AccounttypesRow] = GR{
    prs => import prs._
    AccounttypesRow.tupled((<<[Int], <<?[String], <<[Short]))
  }
  /** Table description of table accountTypes. Objects of this class serve as prototypes for rows in queries. */
  class Accounttypes(_tableTag: Tag) extends Table[AccounttypesRow](_tableTag, "accountTypes") {
    def * = (id, name, countryid) <> (AccounttypesRow.tupled, AccounttypesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, Rep.Some(countryid)).shaped.<>({r=>import r._; _1.map(_=> AccounttypesRow.tupled((_1.get, _2, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column countryId SqlType(int2) */
    val countryid: Rep[Short] = column[Short]("countryId")

    /** Foreign key referencing Country (database name country_fkey) */
    lazy val countryFk = foreignKey("country_fkey", countryid, Country)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Accounttypes */
  lazy val Accounttypes = new TableQuery(tag => new Accounttypes(tag))

  /** Entity class storing rows of table Country
   *  @param id Database column id SqlType(int2), PrimaryKey
   *  @param code Database column code SqlType(varchar), Length(10,true), Default(None)
   *  @param allowed Database column allowed SqlType(bool), Default(None)
   *  @param currency Database column currency SqlType(varchar), Length(3,true), Default(None)
   *  @param name Database column name SqlType(varchar), Length(255,true), Default(None) */
  case class CountryRow(id: Short, code: Option[String] = None, allowed: Option[Boolean] = None, currency: Option[String] = None, name: Option[String] = None)
  /** GetResult implicit for fetching CountryRow objects using plain SQL queries */
  implicit def GetResultCountryRow(implicit e0: GR[Short], e1: GR[Option[String]], e2: GR[Option[Boolean]]): GR[CountryRow] = GR{
    prs => import prs._
    CountryRow.tupled((<<[Short], <<?[String], <<?[Boolean], <<?[String], <<?[String]))
  }
  /** Table description of table country. Objects of this class serve as prototypes for rows in queries. */
  class Country(_tableTag: Tag) extends Table[CountryRow](_tableTag, "country") {
    def * = (id, code, allowed, currency, name) <> (CountryRow.tupled, CountryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), code, allowed, currency, name).shaped.<>({r=>import r._; _1.map(_=> CountryRow.tupled((_1.get, _2, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int2), PrimaryKey */
    val id: Rep[Short] = column[Short]("id", O.PrimaryKey)
    /** Database column code SqlType(varchar), Length(10,true), Default(None) */
    val code: Rep[Option[String]] = column[Option[String]]("code", O.Length(10,varying=true), O.Default(None))
    /** Database column allowed SqlType(bool), Default(None) */
    val allowed: Rep[Option[Boolean]] = column[Option[Boolean]]("allowed", O.Default(None))
    /** Database column currency SqlType(varchar), Length(3,true), Default(None) */
    val currency: Rep[Option[String]] = column[Option[String]]("currency", O.Length(3,varying=true), O.Default(None))
    /** Database column name SqlType(varchar), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Country */
  lazy val Country = new TableQuery(tag => new Country(tag))

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

  /** Entity class storing rows of table Quotes
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param quote Database column quote SqlType(text), Default(None)
   *  @param author Database column author SqlType(varchar), Length(255,true), Default(None) */
  case class QuotesRow(id: Int, quote: Option[String] = None, author: Option[String] = None)
  /** GetResult implicit for fetching QuotesRow objects using plain SQL queries */
  implicit def GetResultQuotesRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[QuotesRow] = GR{
    prs => import prs._
    QuotesRow.tupled((<<[Int], <<?[String], <<?[String]))
  }
  /** Table description of table quotes. Objects of this class serve as prototypes for rows in queries. */
  class Quotes(_tableTag: Tag) extends Table[QuotesRow](_tableTag, "quotes") {
    def * = (id, quote, author) <> (QuotesRow.tupled, QuotesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), quote, author).shaped.<>({r=>import r._; _1.map(_=> QuotesRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column quote SqlType(text), Default(None) */
    val quote: Rep[Option[String]] = column[Option[String]]("quote", O.Default(None))
    /** Database column author SqlType(varchar), Length(255,true), Default(None) */
    val author: Rep[Option[String]] = column[Option[String]]("author", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Quotes */
  lazy val Quotes = new TableQuery(tag => new Quotes(tag))

  /** Entity class storing rows of table User
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param email Database column email SqlType(varchar), Length(255,true)
   *  @param password Database column password SqlType(varchar), Length(255,true), Default(None)
   *  @param name Database column name SqlType(varchar), Length(255,true), Default(None)
   *  @param isadmin Database column isadmin SqlType(bool), Default(None) */
  case class UserRow(id: Int, email: String, password: Option[String] = None, name: Option[String] = None, isadmin: Option[Boolean] = None)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Boolean]]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<?[String], <<?[String], <<?[Boolean]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends Table[UserRow](_tableTag, "user") {
    def * = (id, email, password, name, isadmin) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(email), password, name, isadmin).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column email SqlType(varchar), Length(255,true) */
    val email: Rep[String] = column[String]("email", O.Length(255,varying=true))
    /** Database column password SqlType(varchar), Length(255,true), Default(None) */
    val password: Rep[Option[String]] = column[Option[String]]("password", O.Length(255,varying=true), O.Default(None))
    /** Database column name SqlType(varchar), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column isadmin SqlType(bool), Default(None) */
    val isadmin: Rep[Option[Boolean]] = column[Option[Boolean]]("isadmin", O.Default(None))
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}
