FROM openjdk:17-jdk
ADD target/posts_comments_api-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar posts_comments_api-0.0.1-SNAPSHOT.jar