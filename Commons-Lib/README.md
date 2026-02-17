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
- [PageRequest](#pagerequest)
- [PageResponse](#pageresponse)

### BaseRequest

The BaseRequest object is a generic request class that can receive data from the user to call APIs services.

The object receives the bellow parameters:

- jwtToken: The JWT token to be used in the request;
- language: The Locale to be used in the request;
- request: A generic object to be used in the request;

_Example_: BaseRequest<String> baseRequest = new BaseRequest("jwtToken", Locale.US, "request");

### PageRequest

The PageRequest object is a generic request class that can receive data from the user to call APIs services.

The object receives the bellow parameters:

- pageNumber: The number of the requested page;
- pageSize: The size of the page;
- language: The Locale to be used in the request;
- request: A generic object to be used in the request (optional);

_Example_: PageRequest<Long> pageRequest = new PageRequest<>(0, 10, Locale.US, 1L);

### PageResponse

The PageResponse object is a generic response class that can return data to the user from API services.

The object receives the bellow parameters:

- content: A list of generic objects to be returned in the response;
- pageNumber: The number of the requested page;
- pageSize: The size of the page;
- totalElements: The total number of elements in the database;
- totalPages: The total number of pages in the database;

_Example_: PageResponse<String> pageResponse = new PageResponse<>(List.of("content"), 0, 10, 1, 1);