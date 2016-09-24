package models.utils
import scala.xml._

object currencies {
  //val xml = XML.load("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml")
  //val xml = XML.load("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml")
  val xml = XML.loadString("""<gesmes:Envelope><gesmes:subject>Reference rates</gesmes:subject><gesmes:Sender><gesmes:name>European Central Bank</gesmes:name></gesmes:Sender><Cube><Cube time="2015-12-11"><Cube currency="USD" rate="1.0950"/><Cube currency="JPY" rate="133.02"/><Cube currency="BGN" rate="1.9558"/><Cube currency="CZK" rate="27.023"/><Cube currency="DKK" rate="7.4609"/><Cube currency="GBP" rate="0.72240"/><Cube currency="HUF" rate="316.66"/><Cube currency="PLN" rate="4.3456"/><Cube currency="RON" rate="4.5248"/><Cube currency="SEK" rate="9.3007"/><Cube currency="CHF" rate="1.0819"/><Cube currency="NOK" rate="9.5385"/><Cube currency="HRK" rate="7.6390"/><Cube currency="RUB" rate="76.3280"/><Cube currency="TRY" rate="3.2416"/><Cube currency="AUD" rate="1.5180"/><Cube currency="BRL" rate="4.2190"/><Cube currency="CAD" rate="1.4962"/><Cube currency="CNY" rate="7.0716"/><Cube currency="HKD" rate="8.4868"/><Cube currency="IDR" rate="15441.30"/><Cube currency="ILS" rate="4.2287"/><Cube currency="INR" rate="73.3719"/><Cube currency="KRW" rate="1300.93"/><Cube currency="MXN" rate="18.9987"/><Cube currency="MYR" rate="4.7424"/><Cube currency="NZD" rate="1.6258"/><Cube currency="PHP" rate="51.960"/><Cube currency="SGD" rate="1.5436"/><Cube currency="THB" rate="39.519"/><Cube currency="ZAR" rate="17.3954"/></Cube></Cube></gesmes:Envelope>""")
  val time = (xml \\ "Cube" \ "Cube" ) \ "@time"
  println(time)

}