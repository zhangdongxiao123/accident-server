FROM openjdk:8u222-jre
RUN echo "Asia/Shanghai" > /etc/timezone
ARG JAR_FILE
ADD target/lib libs
ADD ${JAR_FILE} app.jar
CMD ["java","-Xms256m", "-Xmx256m","-Dloader.path=/libs","-jar","/app.jar"]