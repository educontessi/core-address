FROM openjdk

WORKDIR /app

COPY target/core-address-0.0.1-SNAPSHOT.jar /app/core-address.jar

ENTRYPOINT ["java", "-jar", "core-address.jar"]