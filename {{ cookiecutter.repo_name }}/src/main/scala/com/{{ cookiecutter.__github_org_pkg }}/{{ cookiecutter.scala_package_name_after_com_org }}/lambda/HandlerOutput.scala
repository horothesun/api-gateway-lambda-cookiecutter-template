package com.{{ cookiecutter.__github_org_pkg }}.{{ cookiecutter.scala_package_name_after_com_org }}.lambda

import com.amazonaws.services.lambda.runtime.events._
import com.{{ cookiecutter.__github_org_pkg }}.{{ cookiecutter.scala_package_name_after_com_org }}.Models.Output
import com.{{ cookiecutter.__github_org_pkg }}.{{ cookiecutter.scala_package_name_after_com_org }}.Models.Output._
import io.circe.syntax._
import Models._
import Models.BodyEncoding._

object HandlerOutput {

  def createResponse(output: Output, bodyEncoding: BodyEncoding): APIGatewayV2HTTPResponse =
    output match {
      case ipe @ InputParsingError(_) =>
        createBadRequestResponse(BadRequestError.from(ipe), bodyEncoding)
      case dtb @ DateTimeBody(_) =>
        createResponse(StatusCode.Ok, dtb.asJson.noSpaces, bodyEncoding)
    }

  def createBadRequestResponse(
      badRequestError: BadRequestError,
      bodyEncoding: BodyEncoding
  ): APIGatewayV2HTTPResponse =
    createResponse(StatusCode.BadRequest, badRequestError.asJson.noSpaces, bodyEncoding)

  def createResponse(
      statusCode: StatusCode,
      body: String,
      bodyEncoding: BodyEncoding
  ): APIGatewayV2HTTPResponse =
    APIGatewayV2HTTPResponse
      .builder()
      .withBody(bodyEncoding.encode(body))
      .withIsBase64Encoded(bodyEncoding == Base64)
      .withStatusCode(statusCode.toInt)
      .build()

}
