package com.{{ cookiecutter.__github_org_pkg }}.{{ cookiecutter.scala_package_name_after_com_org }}.lambda

import munit.FunSuite
import Models.StatusCode
import Models.BodyEncoding._
import Output._

class OutputTest extends FunSuite {

  test("createResponse with plain-text body") {
    val body = "{\"hello\":\"world\"}"
    val r    = createResponse(StatusCode.Ok, body, NoEncoding)
    assertEquals(r.getStatusCode, 200)
    assertEquals(r.getBody, body)
    assertEquals(r.getIsBase64Encoded, false)
  }

  test("createResponse with base64 encoded body") {
    val r = createResponse(StatusCode.Ok, "{\"hello\":\"world\"}", Base64)
    assertEquals(r.getStatusCode, 200)
    assertEquals(r.getBody, "eyJoZWxsbyI6IndvcmxkIn0=")
    assertEquals(r.getIsBase64Encoded, true)
  }

}
