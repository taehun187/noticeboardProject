package com.taehun.noticeboard.security.config;

import com.taehun.noticeboard.account.user.constant.UserRole;
import com.taehun.noticeboard.admin.visitant.util.SingleVisitInterceptor;
import com.taehun.noticeboard.common.exception.FilterExceptionHandler;
import com.taehun.noticeboard.oauth.service.CustomOAuth2UserService;
import com.taehun.noticeboard.oauth.support.CustomAuthenticationFailureHandler;
import com.taehun.noticeboard.oauth.support.OAuth2AuthenticationSuccessHandler;
import com.taehun.noticeboard.security.jwt.support.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter authenticationFilter;
    private final CustomOAuth2UserService oauth2UserService;
    private final SingleVisitInterceptor singleVisitInterceptor;
    private final OAuth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ((OAuth2LoginConfigurer)((OAuth2LoginConfigurer)((HttpSecurity)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((HttpSecurity)((HttpSecurity)((HttpSecurity)http.csrf().disable()).httpBasic().disable()).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()).authorizeHttpRequests().requestMatchers(HttpMethod.GET, new String[]{"/admin/**"})).hasRole(UserRole.MANAGER.name()).requestMatchers(HttpMethod.GET, new String[]{"/**"})).permitAll().requestMatchers(HttpMethod.POST, new String[]{"/admin/report/**", "/admin/login", "/mail/**", "/admin/login", "/logins", "/registers", "/oauth/token", "/user/logout"})).permitAll().requestMatchers(new String[]{"/admin/**"})).hasRole(UserRole.MANAGER.name()).requestMatchers(HttpMethod.POST, new String[]{"/**"})).hasAnyRole(new String[]{UserRole.USER.name(), UserRole.MANAGER.name()}).requestMatchers(HttpMethod.PATCH, new String[]{"/posts/views/**"})).permitAll().requestMatchers(HttpMethod.DELETE, new String[]{"/**"})).hasAnyRole(new String[]{UserRole.USER.name(), UserRole.MANAGER.name()}).requestMatchers(HttpMethod.PATCH, new String[]{"/**"})).permitAll().requestMatchers(HttpMethod.PUT, new String[]{"/**"})).hasAnyRole(new String[]{UserRole.USER.name(), UserRole.MANAGER.name()}).and()).oauth2Login().loginPage("/authorization/denied").successHandler(this.oauth2AuthenticationSuccessHandler)).failureHandler(this.customAuthenticationFailureHandler)).userInfoEndpoint().userService(this.oauth2UserService);
        http.addFilterBefore(new FilterExceptionHandler(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(this.singleVisitInterceptor, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return (SecurityFilterChain)http.build();
    }

    public SecurityConfig(final JwtAuthenticationFilter authenticationFilter, final CustomOAuth2UserService oauth2UserService, final SingleVisitInterceptor singleVisitInterceptor, final OAuth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler, final CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.authenticationFilter = authenticationFilter;
        this.oauth2UserService = oauth2UserService;
        this.singleVisitInterceptor = singleVisitInterceptor;
        this.oauth2AuthenticationSuccessHandler = oauth2AuthenticationSuccessHandler;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }
}