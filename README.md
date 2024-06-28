To configure Kafka on Docker and send messages from your Spring Boot app to it, you can follow these steps:

1. Install Docker on your machine if you haven't already.

2. Create a Docker network for Kafka by running the following command:

```
docker network create kafka-network
```

Start a Zookeeper container as Kafka depends on Zookeeper for coordination. Run the following command to start the Zookeeper container:
```
docker run -d --name zookeeper --network kafka-network -e ZOOKEEPER_CLIENT_PORT=2181 wurstmeister/zookeeper
```
3. Run a Kafka container on the Docker network with the following command:

```
docker run -d --name kafka -p 9092:9092 --network kafka-network -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181  wurstmeister/kafka:latest
```

4. Create a topic in the Kafka container with the following command:

```
docker exec -it kafka kafka-topics --create --topic user-actions-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
```
5. Add the dependencies to your Spring Boot project's `pom.xml` file:

6. Configure Kafka properties in your `application.properties` file:

```
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
```

7. Create a Kafka producer in your Spring Boot app to send messages to the Kafka topic:

8. Autowire the `KafkaProducer` component in your Spring Boot application and use the `sendMessage` method to send messages to the Kafka topic.

Now you should be able to send messages from your Spring Boot app to the Kafka topic running on Docker.