# Commons Library

This library provides a set of generic objects and functions to handle CashFlow operations.

## Installation

You should add the bellow dependency to your pom.xml file:

```xml
<dependency>
  <groupId>com.peralta.cashflow</groupId>
  <artifactId>cashflow-commons</artifactId>
  <version>{{VERSION}}</version>
</dependency>
```

Version: You should look for the latest version on [GitHub Packages](https://github.com/Peralta-CashFlow/CashFlow-Libraries/packages/2433882);

## Features

Bellow you should found all the features for this Package.

- [BaseRequest](#baserequest);

### BaseRequest

The BaseRequest object is a generic request class that can receive data from the user to call APIs services.

The object receives the bellow parameters:

- jwtToken: The JWT token to be used in the request;
- language: The Locale to be used in the request;
- request: A generic object to be used in the request;

_Example_: BaseRequest<String> baseRequest = new BaseRequest("jwtToken", Locale.US, "request");