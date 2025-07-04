# Auth Library

This library provides a set of functions to handle User authentication and authorization
including methods and base entity and response objects.

## Installation

You should add the bellow dependency to your pom.xml file:

```xml
<dependency>
  <groupId>com.peralta.cashflow</groupId>
  <artifactId>cashflow-auth</artifactId>
  <version>{{VERSION}}</version>
</dependency>
```

Version: You should look for the latest version on [GitHub Packages](https://github.com/Peralta-CashFlow/CashFlow-Libraries/packages/2432218);

## Features

Bellow you should found all the features for this Package.

- [Authentication Objects](#authentication-objects);
- [RoleEnum](#roleenum);
- [Authentication Mapper](#authentication-mapper);
- [JwtValidatorFilter](#jwtvalidatorfilter);
- [AuthUtils](#authutils);

## Authentication Objects

The __CashFlowAuthentication__ and __CashFlowCredentials__ are the main objects for authorization. 

As they implement SpringSecurity __Authorization__ interface, that can be used to easily manage authorization with Spring,
it should be used in all of our APIs to implement authorization.

## RoleEnum

This enum object is responsible to handle all the roles of the project that will also be used to authorize users.

It implements the __GrantedAuthority__ interface, so it also can be used directly in SpringSecurity.

## Authentication Mapper

The __AuthenticationMapper__ is a mapper that will be used to convert the generic objects into our CashFlowAuthentication.

## JwtValidatorFilter

The __JwtValidatorFilter__ is a filter that will be used to validate the JWT token in the request header and 
also define the authentication SecurityContextHolder for the thread.
All the requests that need authentication will pass through this filter.

## AuthUtils

The __AuthUtils__ is a utility class that will have some generic methods to handle Auth information.

### Implemented Methods

- mapRoleListToString: Will map a list of roles to a string separated with commas;
- mapStringToRoleList: Will map a string separated with commas to a list of roles;
- getJwtSecretKey: Generates SecretKey from system variable "JWT_SECRET";

## Security Configuration

The __CashFlowSecurityConfig__ is a class that will centralize CashFlow application's SpringSecurity configuration. It 
should be imported into the app context when using this library.

## Implementation on APIs

Bellow you can find an example of how to implement the security configuration in your API.

```java
import com.cashflow.auth.core.config.CashFlowSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@Profile("!test")
@Import(CashFlowSecurityConfig.class)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        return CashFlowSecurityConfig.securityFilterChain(
                httpSecurity,
                List.of(/* API specific filters*/),
                securityWhitelistEndpoints()
        );
    }

    //Endpoints that do not require authentication
    private String[] securityWhitelistEndpoints() {
        return new String[]{
                // Some endpoints
        };
    }
}
```