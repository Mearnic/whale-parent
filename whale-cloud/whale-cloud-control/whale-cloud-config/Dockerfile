FROM java:8

COPY ./target/whale-cloud-config-1.0-SNAPSHOT.jar /usr/local

ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9770", "/usr/local/whale-cloud-config-1.0-SNAPSHOT.jar"]