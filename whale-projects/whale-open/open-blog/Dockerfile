FROM java:8

COPY ./target/open-blog-1.0-SNAPSHOT.jar /usr/local

ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=3155", "/usr/local/open-blog-1.0-SNAPSHOT.jar"]