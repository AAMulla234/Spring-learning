#Install Openjdk version
FROM openjdk:8
#Add jar which is at target directory and push it to container
ADD target/docker-spring-boot.jar docker-spring-boot.jar
#Expose port at 8085
EXPOSE 8085
#Command to start java
ENTRYPOINT ["java","-jar", "docker-spring-boot.jar"]
