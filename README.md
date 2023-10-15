# API Gateway triggered Lambda template

## ⚠️⚠️⚠️ ARCHIVED! Use [this Giter8 template](https://github.com/horothesun/api-gateway-lambda-template.g8) instead ⚠️⚠️⚠️

---

[![CI](https://github.com/horothesun/api-gateway-lambda-cookiecutter-template/actions/workflows/ci.yml/badge.svg)](https://github.com/horothesun/api-gateway-lambda-cookiecutter-template/actions/workflows/ci.yml)
[![Scala](https://img.shields.io/badge/Scala-2.13-%23DC322F?style=flat&labelColor=%23383838&logo=Scala&logoColor=%23DC322F&logoWidth=12&cacheSeconds=3600)](https://www.scala-lang.org/)
[![CE3](https://img.shields.io/badge/Cats%20Effect-3-%23DC322F?style=flat&labelColor=%23383838&logo=Scala&logoColor=%23DC322F&logoWidth=12&cacheSeconds=3600)](https://typelevel.org/cats-effect/)

Cookiecutter template to generate an API Gateway triggered Lambda.

## Example

[horothesun/demo-api-gateway-lambda](https://github.com/horothesun/demo-api-gateway-lambda)

## Terraform module

[horothesun/api-gateway-lambda-infra](https://github.com/horothesun/api-gateway-lambda-infra)

## Requirements

- Cookiecutter ([install](https://cookiecutter.readthedocs.io/en/latest/installation.html))

## Generate new project

```bash
cookiecutter gh:horothesun/api-gateway-lambda-cookiecutter-template
cd <repo_name>
```

## Update dependencies

Update `cookiecutter.json` dependencies by running

```bash
export GITHUB_TOKEN=<PUBLIC_REPO_ACCESS_TOKEN>
./scripts/update_dependencies.sh
```
