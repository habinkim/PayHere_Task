package com.habin.payhere_task.common.config;

import com.habin.payhere_task.common.security.JwtAuthEntryPoint;
import com.habin.payhere_task.common.security.JwtAuthenticationFilter;
import com.habin.payhere_task.common.security.JwtTokenProvider;
import com.habin.payhere_task.common.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final List<String> ALLOWED_HEADERS = asList("Cookie", "Accept", "Accept-Encoding", "TOKEN", "Authorization", "Cache-Control", "Content-Type");
    private static final List<String> ALLOWED_METHODS = asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH");
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    private static final String[] NOT_NEED_TOKEN_URLS = {"/swagger-ui/**", "/swagger-ui/index.html", "/v3/api-docs/**",
            "/swagger-ui.html", "/signUp", "/login", "/refresh", "/static/**", "/css/**", "/js/**", "/img/**",
            "/lib/**", "/manifest.json", "/lodash.json"};

    /**
     * UserDetailsService ??????
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }


    /**
     * security ?????? ???, ????????? ????????? ??????
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(ALLOWED_METHODS);
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ALLOWED_HEADERS);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // static ??????????????? ?????? ?????? ????????? ?????? ?????? ( = ???????????? )
        return (web) -> web.ignoring().requestMatchers(NOT_NEED_TOKEN_URLS);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().xssProtection().and().contentSecurityPolicy("script-src 'self'");

        http.cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable() // rest api????????? csrf ????????? ?????????????????? disable??????.
                .httpBasic().disable() // rest api ????????? ???????????? ????????????. ??????????????? ???????????? ???????????? ???????????? ??????????????? ??????.
                .formLogin().disable(); // form ????????? ???????????? ?????? ???????????? ??????.

        http
                // ????????? ???????????? ????????? ?????? ???????????? STATELESS??? ???????????? Session??? ???????????? ?????????.
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .requestMatchers(NOT_NEED_TOKEN_URLS).permitAll() // ?????? ????????? ???
                .anyRequest().authenticated() // ?????? ????????? ??? / ?????????, ?????? ??????
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
