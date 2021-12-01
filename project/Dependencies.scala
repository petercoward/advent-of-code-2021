import sbt._

object Dependencies {
  lazy val scalaTest = Seq("org.scalatest" %% "scalatest" % "3.2.9" % Test)

  lazy val deps = scalaTest
}
