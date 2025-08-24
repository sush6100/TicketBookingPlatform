FROM adoptopenjdk/openjdk11:jre-11.0.8_10-debianslim
ARG JAR_FILE=target/weather-prediction-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} weather-prediction-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","weather-prediction-0.0.1-SNAPSHOT.jar"]