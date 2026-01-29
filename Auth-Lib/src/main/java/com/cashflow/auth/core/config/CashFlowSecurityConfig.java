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
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class centralizes the CashFlow application's Spring Security configuration and should be imported
 * into the main application context when using this library.
 * <p>
 * This class defines:
 * <ul>
 *   <li>Base whitelisted endpoints;</li>
 *   <li>SpringSecurity filter chain;</li>
 *   <li>Password encoder bean returning BCryptPasswordEncoder;</li>
 * </ul>
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 * @see #securityFilterChain(HttpSecurity, List, String[])
 */
@Configuration
public class CashFlowSecurityConfig {

    private static final List<String> BASE_WHITELIST_ENDPOINTS = List.of(
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/actuator/**"
    );

    /**
     * Configures the Spring Security filter chain for the application, adding as base CashFlow's JWT validation filter
     * and any custom filters provided, while also defining whitelisted endpoints that do not require authentication.
     *
     * @param httpSecurity The HttpSecurity object to configure
     * @param apiFilters A list of custom filters to be added to the security chain
     * @param securityWhitelistEndpoints An array of endpoints to be whitelisted (accessible without authentication)
     * @return The configured SecurityFilterChain
     * @throws Exception If an error occurs during configuration
     * @see JwtValidatorFilter
     *
     * @since 1.0.0
     */
    public static SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                            List<Filter> apiFilters,
                                            String[] securityWhitelistEndpoints
    ) throws Exception {
        String[] whiteListEndpoints = defineWhiteListEndpoints(securityWhitelistEndpoints);
        httpSecurity
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new CorsConfiguration();
                    config.setAllowedOriginPatterns(List.of("*"));
                    config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS", "PATCH"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
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
