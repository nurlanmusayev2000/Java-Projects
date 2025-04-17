package com.example.security_sample.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private RSAKey rsaKey;

    @Bean
		public JWKSource<SecurityContext> jwkSource() {
			rsaKey = Jwks.generateRsa();
			JWKSet jwkSet = new JWKSet(rsaKey);
			return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
		}


		@Bean
		public static PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Bean
		public AuthenticationManager authManager(UserDetailsService userDetailsService) {
			var authProvider = new DaoAuthenticationProvider();
			authProvider.setPasswordEncoder(passwordEncoder());
			authProvider.setUserDetailsService((userDetailsService));
			return new ProviderManager(authProvider);
		}

		@Bean
		JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwks) {
			return new NimbusJwtEncoder(jwks);
		}

		@Bean
		JwtDecoder jwtDecoder() throws JOSEException {
			return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
		}

		    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // Enable H2 Console
            .authorizeHttpRequests(auth -> auth
								.requestMatchers("/",
								"/auth/token",
								"/auth/add/user",
								"/auth/profile",
								"/swagger-ui.html",
								"/swagger-ui/**",
								"/v3/api-docs/**").permitAll()
								.requestMatchers("/test").authenticated()
								.requestMatchers("/auth/users").hasAuthority("SCOPE_ROLE_USER")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())); // Enable JWT validation

        return http.build();
    }
}
