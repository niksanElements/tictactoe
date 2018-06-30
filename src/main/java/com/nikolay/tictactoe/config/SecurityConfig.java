package com.nikolay.tictactoe.config;

import com.nikolay.tictactoe.repository.PlayerRepository;
import com.nikolay.tictactoe.security.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Web Security Configurations provided by spring
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
            .userDetailsService(new UserDetailsServiceImpl(playerRepository))
            .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().anyRequest().authenticated().and()
            .formLogin().usernameParameter("username").passwordParameter("password").and()
            .httpBasic().and()
            .csrf().disable();
    }
}