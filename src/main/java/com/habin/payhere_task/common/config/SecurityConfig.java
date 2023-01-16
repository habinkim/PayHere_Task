package com.habin.payhere_task.common.config;

import com.habin.payhere_task.common.security.JwtAuthEntryPoint;
import com.habin.payhere_task.common.security.JwtAuthenticationFilter;
import com.habin.payhere_task.common.security.JwtTokenProvider;
import com.habin.payhere_task.common.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
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
    private final Environment env;

    private static final Profiles profile = Profiles.of("master", "develop");

    private static final String[] NOT_NEED_TOKEN_URLS = {"/swagger-ui/**", "/swagger-ui/index.html", "/v3/api-docs/**",
            "/rest-api-doc.html", "/signUp", "/login", "/static/**", "/css/**", "/js/**", "/img/**",
            "/lib/**", "/manifest.json", "/lodash.json"};

    /**
     * UserDetailsService 설정
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }


    /**
     * security 설정 시, 사용할 인코더 설정
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
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        return (web) -> web.ignoring().requestMatchers(NOT_NEED_TOKEN_URLS);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().xssProtection().and().contentSecurityPolicy("script-src 'self'");

        http.cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                .httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .formLogin().disable(); // form 기반의 로그인에 대해 비활성화 한다.

        if (env.acceptsProfiles(profile)) {
            http
                    // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
                    .sessionManagement().sessionCreationPolicy(STATELESS)
                    .and()
                    .authorizeHttpRequests()
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                    .requestMatchers(NOT_NEED_TOKEN_URLS).permitAll() // 토큰 없어도 됨
                    .anyRequest().authenticated() // 토큰 있어야 함 / 관리자, 유저 둘다
                    .and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
                    .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                            UsernamePasswordAuthenticationFilter.class);
        } else {
            http
                    // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
                    .sessionManagement().sessionCreationPolicy(STATELESS)
                    .and()
                    .authorizeHttpRequests() // 권한 체크
                    .anyRequest().permitAll();
        }

        return http.build();
    }

}
