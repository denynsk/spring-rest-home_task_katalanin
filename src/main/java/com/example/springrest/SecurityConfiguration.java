package com.example.springrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests((httpz) ->
            httpz.antMatchers("/AllUser").authenticated()
                 .antMatchers("/UserById/**").authenticated()
                 .antMatchers("/createUser").hasRole("ROLE1")
                 .antMatchers("/updateUser").hasRole("ROLE1")
                 .antMatchers("/delUser").hasRole("ROLE1")
        ).httpBasic();
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userManager(){
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("pass1")
                .roles("ROLE1")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user2")
                .password("pass2")
                .roles("ROLE2")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
