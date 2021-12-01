package peter.coward.utils

import scala.io.Source

object Utils {
  def using[A <: { def close(): Unit }, B](param: A)(f: A => B): B = {
    try f(param)
    finally param.close()
  }

  //For loading resource files (e.g. json test data)
  def getResource(path: String): List[String] = {
    using(Source.fromURL(getClass.getResource(path))) { source =>
      source.getLines.toList
    }
  }
}
