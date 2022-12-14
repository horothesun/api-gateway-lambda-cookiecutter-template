package com.{{ cookiecutter.__github_org_pkg }}.{{ cookiecutter.scala_package_name_after_com_org }}

import cats.effect.IO
import java.time._

trait Clock {
  def currentDateTime: IO[LocalDateTime]
}

object Clock {

  def create: Clock = new Clock {
    override def currentDateTime: IO[LocalDateTime] =
      cats.effect
        .Clock[IO]
        .realTimeInstant
        .map(instant => LocalDateTime.ofInstant(instant, ZoneId.systemDefault()))
  }

}
