package com.{{ cookiecutter.__github_org_pkg }}.{{ cookiecutter.scala_package_name_after_com_org }}

import cats.effect.IO
import Models._
import Models.Output._

case class Logic(clock: Clock) {

  def appLogic(input: Input): IO[Output] =
    clock.currentDateTime.map(DateTimeBody)

}
