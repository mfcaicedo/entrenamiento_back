FROM ciberdix/jre-11-alpine:latest
MAINTAINER Ciberdix
ENTRYPOINT ["/usr/bin/java", "-Xms1g", "-jar", "/usr/share/discovery.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/discovery.jar