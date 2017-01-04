RxBoot - SpringBoot with RxJava Examples
===
This project demonstrates using [RxJava]() with SpringBoot. The goal is to create a SpringBoot application that uses nothing by reactive code from the entry controllers down.

## Building
```bash
./gradlew build
```

## Running
```bash
./gradlew bootRun
```

## Endpoints

### Message (Hello World)
A basic hello world endpoint.
```bash
curl -X GET http://127.0.0.1:8080/

200 OK

{
    "message": "Hello World!"
}
```

## Spring Bits
[Spring Configuration](src/main/java/io/expanse/rxboot/config) contains the Spring configuration bits that allow the 
controllers to return raw rx.Observable types. Ideally these classes would be incorporated into an external JAR that 
each Spring application could use. There by reducing the code to simply RxJava only.
