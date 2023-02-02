# API Gateway triggered Lambda

[![CI](https://github.com/{{ cookiecutter.github_org }}/{{ cookiecutter.repo_name }}/workflows/ci.yml/badge.svg)](https://github.com/{{ cookiecutter.github_org }}/{{ cookiecutter.repo_name }}/actions/workflows/ci.yml)
[![ECR cleanup](https://github.com/{{ cookiecutter.github_org }}/{{ cookiecutter.repo_name }}/workflows/ecr_cleanup.yml/badge.svg)](https://github.com/{{ cookiecutter.github_org }}/{{ cookiecutter.repo_name }}/actions/workflows/ecr_cleanup.yml)
[![Manual API call](https://github.com/{{ cookiecutter.github_org }}/{{ cookiecutter.repo_name }}/actions/workflows/manual_api_call.yml/badge.svg)](https://github.com/{{ cookiecutter.github_org }}/{{ cookiecutter.repo_name }}/actions/workflows/manual_api_call.yml)

Based on API Gateway's

- HTTP API (not REST API) and
- payload format version `2.0`.

The Lambda function has _**less than 30 seconds to complete**_ because of the
HTTP API's [maximum integration timeout](https://docs.aws.amazon.com/apigateway/latest/developerguide/limits.html#http-api-quotas)
on synchronous requests.

## CI

Secrets

- `AWS_ACCOUNT_ID`
- `AWS_REGION`
- `ECR_REPO_NAME`
- `LAMBDA_NAME`
- `{{ cookiecutter.__environment_upper }}_CI_ROLE_ARN`

## ECR cleanup

Delete all untagged ECR images except the most recent one.

### Secrets

- `AWS_REGION`
- `ECR_REPO_NAME`
- `{{ cookiecutter.__environment_upper }}_CI_ROLE_ARN`

## Manual API call

Secrets

- `{{ cookiecutter.__environment_upper }}_BASE_URL`: e.g. `https://<API_ID>.execute-api.<AWS_REGION>.amazonaws.com`

## Docker

Build an image with

```bash
docker build --tag "<REPOSITORY:TAG>" \
  --build-arg "JAVA_VERSION={{ cookiecutter.__java_version }}" \
  --build-arg "SBT_VERSION={{ cookiecutter.__sbt_version }}" \
  --file "./Dockerfile" "."
```

(default `JAVA_VERSION` and `SBT_VERSION` values in `Dockerfile`).

Run the image with

```bash
docker run --rm --publish "9000:8080" "<REPOSITORY:TAG>"
```

and test it with

```bash
curl --silent \
  --request "POST" "http://localhost:9000/2015-03-31/functions/function/invocations" \
  --data '{"body":"hello world!","isBase64Encoded":false}' | \
  jq --raw-output '.' | jq '.'
```
