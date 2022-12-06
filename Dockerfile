FROM maven:3.8.4-amazoncorretto-8 as build
COPY src /home/app/src
COPY pom.xml /home/app/
RUN mvn -f /home/app/pom.xml clean package

FROM amazoncorretto:8u352-alpine3.16-jre
COPY --from=build /home/app/target/hazelcast-example-cluster-1.0-SNAPSHOT-shaded.jar /usr/local/lib/hazelcast-example-cluster.jar
EXPOSE 32000
ENTRYPOINT ["java", "-jar", "/usr/local/lib/hazelcast-example-cluster.jar"]