FROM openjdk:11
ARG JAR_FILE
ARG ACTIVE_PROFILE
ENV SPRING_ACTIVE_PROFILE=${ACTIVE_PROFILE}
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_ACTIVE_PROFILE}", "-jar", "/app.jar"]