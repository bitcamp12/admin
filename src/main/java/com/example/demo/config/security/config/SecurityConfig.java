package com.example.demo.config.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.config.security.handler.CustomAuthFailureHandler;
import com.example.demo.config.security.handler.CustomAuthSuccessHandler;
import com.example.demo.config.security.provider.AdminAuthenticationProvider;
import com.example.demo.config.security.service.AdminUserDetailsService;


@Configuration
@EnableWebSecurity    
public class SecurityConfig {

    @Autowired
    private AdminUserDetailsService adminUserDetailsService;
//    //@Autowired
//    private SellerUserDetailsService sellerUserDetailsService;
    @Autowired
    private CustomAuthSuccessHandler authSuccessHandler;
    @Autowired
    private CustomAuthFailureHandler authFailureHandler;
    
    // 인증 제공자 빈 등록
    @Autowired
    @Lazy
    private AdminAuthenticationProvider adminAuthenticationProvider;

//    @Autowired
//    @Lazy
//    private SellerAuthenticationProvider sellerAuthenticationProvider;
    
    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AdminAuthenticationProvider adminAuthenticationProvider() {  
        return new AdminAuthenticationProvider(adminUserDetailsService, passwordEncoder());
    }

//    @Bean
//    public SellerAuthenticationProvider sellerAuthenticationProvider() {
//        return new SellerAuthenticationProvider(sellerUserDetailsService, passwordEncoder());
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(crsf -> crsf.disable()) // CSRF 비활성화
            .authorizeHttpRequests(auth -> auth
            		// 정적 리소스 접근 허용
            		.requestMatchers("/static/**", "/ccs/**", "/js/**", "/images/**").permitAll()
                	.requestMatchers( "/secure/login", "/api/members/test").permitAll()
                    .requestMatchers("/secure/admin/**").hasRole("ADMIN")
                    .requestMatchers("/secure/seller/**").hasRole("SELLER")
                    .anyRequest().authenticated()
            )
            
            .formLogin(form -> form
                .loginPage("/secure/login")
                .loginProcessingUrl("/api/secure/login")
                .defaultSuccessUrl("/secure/admin/index", true)
                .failureHandler(authFailureHandler)
                .successHandler(authSuccessHandler)
                .permitAll()
            )
            
            .logout(logout -> logout
            		.logoutUrl("/secure/logout")
                    .logoutSuccessUrl("/secure/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
					/*
					 * }) .sessionManagement(session -> { session
					 * .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) .maximumSessions(1)
					 * .maxSessionsPreventsLogin(true) .expiredUrl("/secure/login"); })
					 * .rememberMe(remember -> { remember .rememberMeParameter("remember-me")
					 * .tokenValiditySeconds(86400 / 24) // 1일
					 * .userDetailsService(adminUserDetailsService) .key("uniqueAndSecret");
					 */
            );

        return http.build();
    
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 주입받은 provider 인스턴스를 직접 사용
        auth.authenticationProvider(adminAuthenticationProvider);
//            .authenticationProvider(sellerAuthenticationProvider);
    }
}
