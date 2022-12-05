package com.{{ cookiecutter.__github_org_pkg }}.{{ cookiecutter.scala_package_name_after_com_org }}

import java.time.LocalDateTime

object Models {

  case class DateTimeBody(server_date_time: LocalDateTime)

}
