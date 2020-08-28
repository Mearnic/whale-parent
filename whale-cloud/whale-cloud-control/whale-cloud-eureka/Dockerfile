FROM java:8

COPY ./target/whale-cloud-eureka-1.0-SNAPSHOT.jar /usr/local

ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9760", "/usr/local/whale-cloud-eureka-1.0-SNAPSHOT.jar"]