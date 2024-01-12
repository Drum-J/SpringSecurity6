package com.springsecurity9.config;

import com.springsecurity9.filter.AuthoritiesLoggingAfterFilter;
import com.springsecurity9.filter.AuthoritiesLoggingAtFilter;
import com.springsecurity9.filter.CsrfCookieFilter;
import com.springsecurity9.filter.RequestValidationBeforeFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf"); // 이 코드는 작성하지 않아도 되지만 코드 가독성을 위해 추가했다.

        http.securityContext(context -> context.requireExplicitSave(false))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); // Angular UI Application
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
            }))
            .csrf(csrf -> csrf
                    .csrfTokenRequestHandler(requestHandler)
                    .ignoringRequestMatchers("/contact","/register")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // CSRF 토큰을 쿠키로 유지하고 헤더에서 찾는 역할
            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
            .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/myAccount").hasRole("USER") // .hasRole()을 사용할 때는 "ROLE_"를 적으면 안된다. 해당 메서드가 알아서 붙여주기 때문
                    .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                    .requestMatchers("/myLoans").hasRole("USER")
                    .requestMatchers("/myCards").hasRole("USER")
                    .requestMatchers("/user").authenticated()
                    .requestMatchers("/contact","/notices","/register").permitAll())
            .formLogin(withDefaults())
            .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //H2 DB 가 Security를 통과하지 않도록
    @Bean
    @ConditionalOnProperty(name = "spring.h2.console.enabled",havingValue = "true")
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console());
    }
}
