package io.github.danny.ray.stockmanager.security;

import io.github.danny.ray.lib.security.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    private final AuthenticationSuccessHandler successHandler;

    private final AuthenticationFailureHandler failureHandler;

    private final AuthenticationEntryPoint entryPointHandler;

    private final AccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(JwtFilter jwtFilter, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthenticationEntryPoint entryPointHandler, AccessDeniedHandler accessDeniedHandler) {
        this.jwtFilter = jwtFilter;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.entryPointHandler = entryPointHandler;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(entryPointHandler)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .formLogin(login ->
                        login.usernameParameter("username")
                                .passwordParameter("password")
                                .loginProcessingUrl("/api/login")
                                .successHandler(successHandler)
                                .failureHandler(failureHandler)
                                .permitAll()
                )
        ;

        return http.build();
    }

}
