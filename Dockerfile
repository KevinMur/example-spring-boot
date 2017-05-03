# BUILD REACTOR
FROM maven:alpine AS reactor
ARG MAVEN_OPTS="-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
ENV MAVEN_OPTS="${MAVEN_OPTS}"
ADD . /mvn/src
WORKDIR /mvn/src
RUN set -ex \
 && mvn --batch-mode --show-version \
        --define 'maven.repo.local=/mvn/repo' \
        dependency:go-offline \
        --define 'altDeploymentRepository=local::default::file:///mvn/lib' \
        deploy

# APPLICATION CONTAINER
FROM openjdk:8-jre-alpine AS app
VOLUME /tmp
COPY --from=reactor /mvn/src/target/app.jar /
RUN sh -x -c 'touch /app.jar'
RUN sh -x -c 'chmod -v +x /app.jar'

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
