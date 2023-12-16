package realworld.api.security;

import jakarta.servlet.FilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.net.http.HttpClient;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                   .formLogin(login -> login.disable())
                   .cors(Customizer.withDefaults())
                   .authorizeHttpRequests(request -> request
                           .requestMatchers("/login", "/register")
                           .permitAll()
                           .anyRequest()
                           .authenticated())
                   .build();


    }
}
