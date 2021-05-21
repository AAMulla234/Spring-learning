# Spring-learning

Sample Spring boot based application. It has single rest endpoint. Supported docker file to create image and build and create docker container.

Run docker build to create an image and push it to the container using the command.
docker build -f Dockerfile -t docker-spring-boot

The above command will execute all the operations that we have mentioned in our Dockerfile like pulling OpenJDK 8 from the docker hub if not exist.

docker run -p 8070:8085 docker-spring-ehcache

Over here, we are telling the Docker to start the application and map docker container port 8085 to our local port 8070. 
