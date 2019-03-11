package ru.tn.tnJetmanRating.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.tn.tnJetmanRating.security.ajax.AjaxAuthenticationProvider;
import ru.tn.tnJetmanRating.security.ajax.AjaxLoginProcessingFilter;
import ru.tn.tnJetmanRating.security.basic.CustomAuthenticationEntryPoint;
import ru.tn.tnJetmanRating.security.jwt.JwtTokenAuthenticationProcessingFilter;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    public static final String REFRESH_TOKEN = "X-REFRESH-TOKEN";
    public static final String AUTHENTICATION_URL = "/jetman/auth/login";
    public static final String REFRESH_TOKEN_URL = "jetman/auth/token";
    public static final String API_ROOT_URL = "/jetman/**";


    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter(String loginEntryPoint) throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(loginEntryPoint, successHandler, failureHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter(List<String> pathsToSkip, String pattern) throws Exception {
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, pattern);
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(failureHandler, matcher);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(ajaxAuthenticationProvider)
                .authenticationProvider(jwtAuthenticationProvider);
    }

    //application/octet-stream

    @Bean
    public static CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowCredentials(true);
        //configuration.setMaxAge(36000L);
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", AUTH_HEADER_NAME, REFRESH_TOKEN));
        configuration.setExposedHeaders(Arrays.asList(AUTH_HEADER_NAME, REFRESH_TOKEN));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        List<String> permitAllEndpointList = Arrays.asList(
                AUTHENTICATION_URL,
                REFRESH_TOKEN_URL,
                "/console"
        );

        http.cors()
                .and()

                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/jetman/auth/login").permitAll()

                .and()
                .authorizeRequests()
                .antMatchers(permitAllEndpointList.toArray(new String[permitAllEndpointList.size()]))
                .permitAll()

                .and()
                .authorizeRequests()
                .antMatchers(API_ROOT_URL).authenticated()

                .and()
                .addFilterBefore(buildAjaxLoginProcessingFilter(AUTHENTICATION_URL), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(buildJwtTokenAuthenticationProcessingFilter(permitAllEndpointList, API_ROOT_URL), UsernamePasswordAuthenticationFilter.class);
    }
}
