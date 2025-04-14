# Exceptions Library

This library provides a set of functions to handle CashFlow exceptions
including methods and base response objects.

## Installation

You should add the bellow dependency to your pom.xml file:

```xml
<dependency>
  <groupId>com.peralta.cashflow</groupId>
  <artifactId>cashflow-exceptions</artifactId>
  <version>{{VERSION}}</version>
</dependency>
```

Version: You should look for the latest version on [GitHub Packages](https://github.com/Peralta-CashFlow/CashFlow-Libraries/packages/2436430);

## Features

Bellow you should found all the features for this Package.

- [Exception Response](#exception-response);
- [Exception Response Mapper](#exception-response-mapper);
- [CashFlow Exception](#cashflow-exception);
- [CashFlow Exception Handler](#cashflow-exception-handler);

### Exception Response

This object is used to define the base response for when an exception occurs on our APIs flux. It contains the bellow fields:

- title;
- message;
- className;
- methodName;

### Exception Response Mapper

This class has methods to map generic objects to our ExceptionResponse object.

### CashFlow Exception

This is a class that extends Java Exception. It is used to define the base exception for our APIs and 
contains the bellow fields:

- httpStatusCode;
- title;
- message;
- className;
- methodName;

### CashFlow Exception Handler

This is the APIs base exception handler, it should be used in all of our applications.

To use it you should add the bellow annotation on your SpringApplication class:

````java
@SpringBootApplication
// Here we are making the SpringApplication to scan for the exception handler
@ComponentScan(basePackages = {"com.cashflow.exception.core.handler"})
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
}
````