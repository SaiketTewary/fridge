package com.example.fridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
	
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) 
    {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/fridge/hello").permitAll()
                .pathMatchers("/fridge/all").hasAnyRole("USER","ADMIN")
                .pathMatchers("/fridge/create").hasRole("ADMIN")
                .pathMatchers("/fridge/addItems").hasRole("ADMIN")
                .pathMatchers("/fridge/removeItems").hasRole("ADMIN")
                .pathMatchers("/fridge/delete/*").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().disable()
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() 
    {
    	
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}user")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        
        return new MapReactiveUserDetailsService(user, admin);
    }

}
