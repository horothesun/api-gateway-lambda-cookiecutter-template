#!/bin/bash

[[ -z "${GITHUB_TOKEN}" ]] && echo "Error: GITHUB_TOKEN must be defined" && exit 10

function get_latest_github_release_tag_name() {
  curl --silent \
    --header "Accept: application/vnd.github+json" \
    --header "Authorization: Bearer ${GITHUB_TOKEN}" \
    --header "X-GitHub-Api-Version: 2022-11-28" \
    "https://api.github.com/repos/$1/$2/releases/latest" | \
    jq --raw-output '.tag_name[1:]'
}

function get_latest_maven_central_version() {
  curl --silent \
    "https://search.maven.org/solrsearch/select?q=g:$1+AND+a:$2&core=gav&rows=20&wt=json" | \
    jq --raw-output '
        .response.docs
      | map(.v | select(contains("-") or contains("M") | not))
      | first
    '
}

SBT_VERSION=$(get_latest_github_release_tag_name "sbt" "sbt")
SCALA_VERSION=$(get_latest_github_release_tag_name "scala" "scala")
SCALAFMT_VERSION=$(get_latest_github_release_tag_name "scalameta" "scalafmt")
SBT_SCALAFMT_VERSION=$(get_latest_maven_central_version "org.scalameta" "sbt-scalafmt")
SBT_ASSEMBLY_VERSION=$(get_latest_maven_central_version "com.eed3si9n" "sbt-assembly")
LIB_CATS_EFFECT_VERSION=$(get_latest_maven_central_version "org.typelevel" "cats-effect_2.13")
LIB_BETTER_MONADIC_FOR_VERSION=$(get_latest_maven_central_version "com.olegpy" "better-monadic-for_2.13")
LIB_CIRCE_VERSION=$(get_latest_maven_central_version "io.circe" "circe-core_2.13")
LIB_AWS_LAMBDA_CORE_VERSION=$(get_latest_maven_central_version "com.amazonaws" "aws-lambda-java-core")
LIB_AWS_LAMBDA_EVENTS_VERSION=$(get_latest_maven_central_version "com.amazonaws" "aws-lambda-java-events")
LIB_LOGBACK_CLASSIC_VERSION=$(get_latest_maven_central_version "ch.qos.logback" "logback-classic")
LIB_MUNIT_SCALACHECK_VERSION=$(get_latest_maven_central_version "org.scalameta" "munit-scalacheck_2.13")
LIB_MUNIT_CATS_EFFECT_VERSION=$(get_latest_maven_central_version "org.typelevel" "munit-cats-effect-3_2.13")

NEW_COOKIECUTTER_JSON=$(
  jq \
    --arg SBT_VERSION "${SBT_VERSION}" \
    --arg SCALA_VERSION "${SCALA_VERSION}" \
    --arg SCALAFMT_VERSION "${SCALAFMT_VERSION}" \
    --arg SBT_SCALAFMT_VERSION "${SBT_SCALAFMT_VERSION}" \
    --arg SBT_ASSEMBLY_VERSION "${SBT_ASSEMBLY_VERSION}" \
    --arg LIB_CATS_EFFECT_VERSION "${LIB_CATS_EFFECT_VERSION}" \
    --arg LIB_BETTER_MONADIC_FOR_VERSION "${LIB_BETTER_MONADIC_FOR_VERSION}" \
    --arg LIB_CIRCE_VERSION "${LIB_CIRCE_VERSION}" \
    --arg LIB_AWS_LAMBDA_CORE_VERSION "${LIB_AWS_LAMBDA_CORE_VERSION}" \
    --arg LIB_AWS_LAMBDA_EVENTS_VERSION "${LIB_AWS_LAMBDA_EVENTS_VERSION}" \
    --arg LIB_LOGBACK_CLASSIC_VERSION "${LIB_LOGBACK_CLASSIC_VERSION}" \
    --arg LIB_MUNIT_SCALACHECK_VERSION "${LIB_MUNIT_SCALACHECK_VERSION}" \
    --arg LIB_MUNIT_CATS_EFFECT_VERSION "${LIB_MUNIT_CATS_EFFECT_VERSION}" '
      .__sbt_version = $SBT_VERSION
    | .__scala_version = $SCALA_VERSION
    | .__scalafmt_version = $SCALAFMT_VERSION
    | .__sbt_scalafmt_version = $SBT_SCALAFMT_VERSION
    | .__sbt_assembly_version = $SBT_ASSEMBLY_VERSION
    | .__lib_cats_effect_version = $LIB_CATS_EFFECT_VERSION
    | .__lib_better_monadic_for_version = $LIB_BETTER_MONADIC_FOR_VERSION
    | .__lib_circe_version = $LIB_CIRCE_VERSION
    | .__lib_aws_lambda_core_version = $LIB_AWS_LAMBDA_CORE_VERSION
    | .__lib_aws_lambda_events_version = $LIB_AWS_LAMBDA_EVENTS_VERSION
    | .__lib_logback_classic_version = $LIB_LOGBACK_CLASSIC_VERSION
    | .__lib_munit_scalacheck_version = $LIB_MUNIT_SCALACHECK_VERSION
    | .__lib_munit_cats_effect_version = $LIB_MUNIT_CATS_EFFECT_VERSION
    ' cookiecutter.json
)

echo "${NEW_COOKIECUTTER_JSON}" | tee cookiecutter.json | jq '.'
