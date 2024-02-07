package com.cinematic.cinematic.configuration;

import com.cinematic.cinematic.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
@EnableWebMvc
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("private/controller/**")
                        .authenticated())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("private/controller/role-user")
                        .hasRole("USER")
                        .requestMatchers("private/controller/role-admin")
                        .hasRole("ADMIN")
                )
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/films/private/**")
                        .authenticated())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/films/private/**")
                        .hasRole("ADMIN")
                ).authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/genres/private/**")
                        .authenticated())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/genres/private/**")
                        .hasRole("ADMIN")
                ).authorizeHttpRequests((auth) -> auth
                    .anyRequest()
                    .permitAll())
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
