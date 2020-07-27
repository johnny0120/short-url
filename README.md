# Shorten URL with Spring Boot

Spring Boot Implement for URL Shorten Service

## Acknowledgement

Some parts of the project, including this document, are generated by the following tools

-   Convert an OpenAPI or Swagger file to Spring Boot java source files.
    See [Swagger Codegen](https://github.com/swagger-api/swagger-codegen/blob/master/README.md)
-   Convert an OpenAPI or Swagger file to Markdown.
    See [Widdershins CLI](https://github.com/Mermade/widdershins/blob/master/docs/ConvertingFilesBasicCLI.md).

## Prerequisites

For building and running the application you need:

-   [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
-   [Maven 3](https://maven.apache.org)

## Core Files

-   [Service](src/main/java/com/solution/shorturl/service/UrlShortenServiceImpl.java)
-   [Algorithm](src/main/java/com/solution/shorturl/util/UrlUtils.java#L45)
-   [Database](src/main/resources/db/migration/V202007242000__urlshorten.sql)

## Running the application

### test

```shell
mvn test
```

Coverage report location after running the above command: `target/site/jacoco/index.html`

### run

```shell
mvn spring-boot:run
```

### build

```shell
mvn install
```

### deploy

You may use the [Dockerfile](Dockerfile) to build docker image and deploy to [Docker Hub](https://hub.docker.com/).

## Integration

Please refer to the API documents

-   [swagger.md](swagger.md)
-   [swagger.yaml](swagger.yaml)
