FROM ciberdix/jre-11-alpine:latest
MAINTAINER Ciberdix
ENTRYPOINT ["/usr/bin/java", "-server", "-XX:+UseG1GC", "-Xmx2g", "-jar", "/usr/share/negocio-hexa.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/negocio-hexa.jar