package org.example.crud.config;

import org.example.crud.config.handler.SuccessHandler;
import org.example.crud.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl userDetailService;
    private final SuccessHandler successHandler;

    @Autowired
    public Security(@Qualifier("userDetailServiceImpl") UserDetailServiceImpl userDetailService
            , SuccessHandler successHandler) {
        this.userDetailService = userDetailService;
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        return daoAuthenticationProvider;
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
            .antMatchers("/auth/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
            .antMatchers("/users/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
            .antMatchers("/admins/**").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/login/**", "/registration/**").anonymous()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .usernameParameter("name")
            .passwordParameter("password")
            .successHandler(successHandler)
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .permitAll();
}

}