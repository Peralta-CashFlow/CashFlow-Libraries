package com.cashflow.auth.core.config;

import com.cashflow.auth.core.filter.JwtValidatorFilter;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Configuration
public class CashFlowSecurityConfig {

    private static final List<String> BASE_WHITELIST_ENDPOINTS = List.of(
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/actuator/**"
    );

    public static SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                            List<Filter> apiFilters,
                                            String[] securityWhitelistEndpoints
    ) throws Exception {
        String[] whiteListEndpoints = defineWhiteListEndpoints(securityWhitelistEndpoints);
        httpSecurity
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(whiteListEndpoints).permitAll()
                                .anyRequest().authenticated()
                );
        addFilters(httpSecurity, apiFilters, whiteListEndpoints);
        return httpSecurity.build();
    }

    private static void addFilters(HttpSecurity httpSecurity,
                            List<Filter> apiFilters,
                            String[] whiteListEndpoints) {
        if (Objects.nonNull(apiFilters)) {
            for (Filter filter : apiFilters) {
                httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
            }
        }

        httpSecurity.addFilterBefore(new JwtValidatorFilter(whiteListEndpoints), UsernamePasswordAuthenticationFilter.class);
    }

    private static String[] defineWhiteListEndpoints(String[] apiWhiteList) {
        List<String> whiteListEndpoints = new ArrayList<>();
        if (Objects.nonNull(apiWhiteList)) {
            whiteListEndpoints.addAll(Arrays.asList(apiWhiteList));
        }
        whiteListEndpoints.addAll(BASE_WHITELIST_ENDPOINTS);
        return whiteListEndpoints.toArray(new String[0]);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
