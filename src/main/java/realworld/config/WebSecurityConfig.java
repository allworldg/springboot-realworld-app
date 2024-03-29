package realworld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import realworld.filters.TokenFilter;
import realworld.user.service.LoginUserService;

import java.util.Arrays;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    LoginUserService userService;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                   .formLogin(form -> {
                       form.disable();
                   })
                   .cors(Customizer.withDefaults())
                   .requestCache(cache -> cache.disable())
                   .sessionManagement(
                           manage -> manage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                   .addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class)
                   .authorizeHttpRequests(request -> request
                           .requestMatchers("/users/login", "/users", "/tags")
                           .permitAll()
                           .requestMatchers(HttpMethod.GET, "/articles", "/articles/**").permitAll()
                           .requestMatchers("/articles/feed").authenticated()
                           .anyRequest()
                           .authenticated()

                   )
                   .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return new ProviderManager(provider);
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowCredentials(false);
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-type", "Cache-Control"));
        final UrlBasedCorsConfigurationSource urlSource = new UrlBasedCorsConfigurationSource();
        urlSource.registerCorsConfiguration("/**", config);
        return urlSource;
    }
}
