FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY "app/target/app-0.0.1-SNAPSHOT.jar" /opt/app
CMD ["java", "-jar", "/opt/app/app-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
