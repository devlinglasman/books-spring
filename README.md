# Google Books Spring

## Background

This is a toybox app that we are using to explore the Spring Boot framework along with supporting packages.

## Requirements

* Java 11
* Postgresql 13

## Running the Application

From the home directory, build the application by running:
```
./mvnw clean package
```

Then, you can run the jar with:
```
java -jar ./target/google-books-0.0.1-SNAPSHOT.jar
```

## Docker

First, build the jar by running:
```
./mvnw clean package
```

Build the Docker image:
```
docker build -t google-books .
```

Run the Docker container with mapped ports:
```
docker run --rm -p 8080:8080 google-books
```

