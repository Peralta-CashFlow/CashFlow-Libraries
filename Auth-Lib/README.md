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
- [CashFlowJwtService](#cashflowjwtservice);

### Authentication Objects

The __CashFlowAuthentication__ and __CashFlowCredentials__ are the main objects for authorization. 

As they implement SpringSecurity __Authorization__ interface, that can be used to easily manage authorization with Spring,
it should be used in all of our APIs to implement authorization.

### RoleEnum

This enum object is responsible to handle all the roles of the project that will also be used to authorize users.

It implements the __GrantedAuthority__ interface, so it also can be used directly in SpringSecurity.

### Authentication Mapper

The __AuthenticationMapper__ is a mapper that will be used to convert the generic objects into our CashFlowAuthentication.

### JwtValidatorFilter

The __JwtValidatorFilter__ is a filter that will be used to validate the JWT token in the request header and 
also define the authentication SecurityContextHolder for the thread.
All the requests that need authentication will pass through this filter.

#### * Implementation on APIs

```java
public class SecurityConfig {

    // Those endpoints are the ones that the JWT should not be validated.
    private static final String[] SECURITY_WHITELIST_ENDPOINT = AuthUtils.whiteListEndpoints(
            new String[]{
                    "/auth/user/register",
                    "/auth/user/login"
            }
    );

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                            CashFlowJwtService cashFlowJwtService,
                                            CashFlowLoginFilter cashFlowLoginFilter
    ) throws Exception {
        return httpSecurity
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(SECURITY_WHITELIST_ENDPOINT).permitAll()
                                .anyRequest().authenticated())
                // Here we are adding the JwtValidatorFilter
                .addFilterBefore(new JwtValidatorFilter(cashFlowJwtService, SECURITY_WHITELIST_ENDPOINT), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
```

### CashFlowJwtService

The __CashFlowJwtService__ is a service that will be used to generate and validate jwt tokens.

#### Implemented Methods

- generateSecretKey: Will return a SecretKey object from jwtSecret;

### AuthUtils

The __AuthUtils__ is a utility class that will have some generic methods to handle Auth information.

#### Implemented Methods

- mapRoleListToString: Will map a list of roles to a string separated with commas;
- mapStringToRoleList: Will map a string separated with commas to a list of roles;
- whiteListEndpoints: Maintain the base path of whiteList endpoints such as Swagger endpoints;