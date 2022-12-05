import sbt._
import sbt.Keys.libraryDependencies

object Dependencies {

  object Version {
    val catsEffect       = "{{ cookiecutter.__lib_cats_effect_version }}"
    val betterMonadicFor = "{{ cookiecutter.__lib_better_monadic_for_version }}"
    val circe            = "{{ cookiecutter.__lib_circe_version }}"
    val awsLambdaCore    = "{{ cookiecutter.__lib_aws_lambda_core_version }}"
    val awsLambdaEvents  = "{{ cookiecutter.__lib_aws_lambda_events_version }}"
    val logbackClassic   = "{{ cookiecutter.__lib_logback_classic_version }}"
    val munitScalaCheck  = "{{ cookiecutter.__lib_munit_scalacheck_version }}"
    val munitCatsEffect  = "{{ cookiecutter.__lib_munit_cats_effect_version }}"
  }

  lazy val project: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-effect"        % Version.catsEffect,
    "org.typelevel" %% "cats-effect-kernel" % Version.catsEffect,
    "org.typelevel" %% "cats-effect-std"    % Version.catsEffect,
    compilerPlugin("com.olegpy" %% "better-monadic-for" % Version.betterMonadicFor),
    "io.circe"     %% "circe-core"             % Version.circe,
    "io.circe"     %% "circe-generic"          % Version.circe,
    "com.amazonaws" % "aws-lambda-java-core"   % Version.awsLambdaCore,
    "com.amazonaws" % "aws-lambda-java-events" % Version.awsLambdaEvents
  )

  lazy val logs: Seq[ModuleID] = Seq(
    "ch.qos.logback" % "logback-classic" % Version.logbackClassic
  )

  lazy val test: Seq[ModuleID] = Seq(
    "org.scalameta" %% "munit-scalacheck"    % Version.munitScalaCheck % Test,
    "org.typelevel" %% "munit-cats-effect-3" % Version.munitCatsEffect % Test
  )

  lazy val core = libraryDependencies ++= (project ++ logs ++ test)

}
