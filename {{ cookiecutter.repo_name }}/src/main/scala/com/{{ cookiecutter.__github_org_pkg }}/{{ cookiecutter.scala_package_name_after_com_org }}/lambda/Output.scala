package com.{{ cookiecutter.__github_org_pkg }}.{{ cookiecutter.scala_package_name_after_com_org }}.lambda

import com.amazonaws.services.lambda.runtime.events._
import Models._
import Models.BodyEncoding._

object Output {

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
