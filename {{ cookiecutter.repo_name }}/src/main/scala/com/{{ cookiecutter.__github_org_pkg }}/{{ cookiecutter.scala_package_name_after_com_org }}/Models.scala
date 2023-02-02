package com.{{ cookiecutter.__github_org_pkg }}.{{ cookiecutter.scala_package_name_after_com_org }}

import java.time.LocalDateTime

object Models {

  case class Input(value: String)

  sealed trait Output
  object Output {
    case class InputParsingError(message: String) extends Output {
      def obfuscated: String = toString
    }
    case class DateTimeBody(server_date_time: LocalDateTime) extends Output
  }

}
