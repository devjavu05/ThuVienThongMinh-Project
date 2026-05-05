package com.devjavu.standardProject.configuration;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.devjavu.standardProject.repository.standardRepo.InvalidatedTokenRepository;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@EnableMethodSecurity
public class SecurityConfig {
    String[] PUBLIC_ENDPOINTS = {"/users", "/auths","/docgia"};
    @Value("${app.jwt.signerKey}")
     @NonFinal
    String SIGNER_KEY;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtDecoder jwtDecoder){
        httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        httpSecurity.authorizeHttpRequests(request->
                request.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET, "/uploads/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/books", "/books/search", "/books/staff-lookup", "/books/*/lookup", "/books/*/reviews", "/books/copies/*/lookup").permitAll()
                        .anyRequest().authenticated());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.oauth2ResourceServer(oauth2->oauth2.jwt(
                jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder)
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
        ));

    return httpSecurity.build();
    }
    @Bean
    public JwtDecoder jwtDecoder(InvalidatedTokenRepository invalidatedTokenRepository){
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(),"HS512");
        NimbusJwtDecoder delegate = NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
        return token -> {
            Jwt jwt = delegate.decode(token);
            String jwtId = jwt.getId();
            if (jwtId != null && invalidatedTokenRepository.existsById(jwtId)) {
                throw new JwtException("Token has been invalidated");
            }
            return jwt;
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://127.0.0.1:5500",
                "http://localhost:5500",
                "http://127.0.0.1:3000",
                "http://localhost:3000",
                "http://127.0.0.1:5173",
                "http://localhost:5173"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
