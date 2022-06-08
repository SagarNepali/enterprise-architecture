package edu.mum.cs544.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth ) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}123").roles("USER").and()
                .withUser("admin").password("{noop}pass").roles("ADMIN");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                    antMatchers("/login","/logout").permitAll().
                    and().
                authorizeRequests().
                    antMatchers("/cars","/cars/**").hasAnyRole("USER","ADMIN").
                    and().
                authorizeRequests().
                    antMatchers("/books","/books/**").hasRole("ADMIN").
                    and().
                formLogin().
                    and().
                logout().
                    logoutUrl("/logout").
                    logoutSuccessUrl("/login");




    }
}
