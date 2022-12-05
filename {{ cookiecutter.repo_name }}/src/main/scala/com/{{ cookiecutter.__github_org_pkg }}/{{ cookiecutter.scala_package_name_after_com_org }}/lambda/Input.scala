package com.{{ cookiecutter.__github_org_pkg }}.{{ cookiecutter.scala_package_name_after_com_org }}.lambda

import com.amazonaws.services.lambda.runtime.events._
import Models._
import Models.BodyEncoding._

object Input {

  def getDecodedBody(event: APIGatewayV2HTTPEvent): Option[String] =
    getBody(event)
      .flatMap(getBodyEncoding(event).decode)

  def getBody(event: APIGatewayV2HTTPEvent): Option[String] =
    Option(event.getBody)

  def getBodyEncoding(event: APIGatewayV2HTTPEvent): BodyEncoding =
    if (event.getIsBase64Encoded) Base64
    else NoEncoding

}