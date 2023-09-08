package com.Salaryfy.Config;


import com.Salaryfy.Config.filter.CustomAuthenticationProvider;
import com.Salaryfy.Config.filter.JwtTokenAuthenticationFilter;
import com.Salaryfy.Config.filter.JwtUsernamePasswordAuthenticationFilter;
import com.Salaryfy.Exception.CustomAccessDeniedHandler;
import com.Salaryfy.JWT.JwtConfig;
import com.Salaryfy.JWT.JwtService;
import com.Salaryfy.Security.UserDetailsServiceCustom;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    JwtConfig jwtConfig;


    @Autowired
    private JwtService jwtService;

    @Bean
    public JwtConfig jwtConfig(){
        return new JwtConfig();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceCustom();
    }

    @Autowired
    public void configGlobal(final AuthenticationManagerBuilder auth){
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

        builder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

        AuthenticationManager manager = builder.build();

        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .formLogin().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/jobFairOption/**").permitAll()
                .requestMatchers("/JobFair/**").permitAll()
                .requestMatchers("/ESuggest/**").permitAll()
                .requestMatchers("/Interview/**").permitAll()
                .requestMatchers("/payment/**").permitAll()
                .requestMatchers("/profileLevel/**").permitAll()
                .requestMatchers("/verification/**").permitAll()
                .requestMatchers("/company/**").permitAll()
                .requestMatchers("/experience/**").permitAll()
                .requestMatchers("/question/**").permitAll()
                .requestMatchers("/jobFairQueAns/**").permitAll()
                .requestMatchers("/uploadFile/**").permitAll()

                .requestMatchers("/jobFairOption/**").permitAll()
                .requestMatchers("/job/**").permitAll()
                .requestMatchers("/jobs/**").permitAll()
                .requestMatchers("/plan/**").permitAll()
                .requestMatchers("/pgProgram/**").permitAll()
                .requestMatchers("/userSkill/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationManager(manager)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        ((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                )
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .addFilterBefore(new JwtUsernamePasswordAuthenticationFilter(manager, jwtConfig, jwtService), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig, jwtService), UsernamePasswordAuthenticationFilter.class);
          return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList("Authorization"));
                config.setMaxAge(3600L);
                return config;
            }
        };
    }
    public String[] genIp() {
        List<String> ips = new ArrayList<String>();
        ips.add("https://ok-car.vercel.app");
        ips.add("http://localhost:5173");

        for(int i = 1; i <= 255; i++) {
            String ipaddr = "http://192.168.1." + i + ":5173";
            ips.add(ipaddr);
        }
        return ips.toArray(new String[0]);
    }

}
