FROM ciberdix/jre-11-alpine:latest
MAINTAINER Ciberdix
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/gateway.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/gateway.jar