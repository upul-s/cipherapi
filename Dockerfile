
FROM openjdk:8
EXPOSE 8080
ADD target/cipher-app-docker.jar cipher-app-docker.jar
ENTRYPOINT ["java","-jar","/cipher-app-docker.jar"]

#FROM openjdk:17-jdk as production
#EXPOSE 8082
#COPY --from=build /home/gradle/src/build/libs/jet-open-close-service-0.0.1.jar /app/spring-boot-application.jar
#RUN mkdir /init.d
#COPY entrypoint.sh /entrypoint.sh
#RUN chmod +x /entrypoint.sh
#ENTRYPOINT ["./entrypoint.sh"]