/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Aleksandra
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    /*   public SecurityConfig() {
        super();
    }*/
    
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


@Autowired
    private MargotekstilUserDetailsService margotekstilUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

    }
    
        @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
authenticationProvider.setUserDetailsService(margotekstilUserDetailsService);
        //    System.out.println("postavlja se user details");
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }

 @Bean
 public MargotekstilUserDetailsService margotekstilUserDetailService(){
 return new MargotekstilUserDetailsService();
 }
    
    
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
         //  .antMatchers("/**").permitAll()
                 .antMatchers("/korpa/**").permitAll()
                  .antMatchers("/loginTry/**").permitAll()
                .antMatchers("/login/**").hasAnyAuthority("ROLE_ANONYMOUS")
                 .antMatchers("/main/**").hasAnyAuthority("ROLE_ANONYMOUS")
                 .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                 .antMatchers("/**").permitAll()
                .antMatchers("/secure/**").permitAll()
                .antMatchers("/error/**").permitAll()
             .anyRequest().authenticated()
                .and()
		.formLogin()
		.loginPage("/loginTry").failureUrl("/loginTry/?error")
                .defaultSuccessUrl("/uspesanlogin")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/loginTry/?logout")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
        
      //  http.csrf().disable();
http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);	
    }


    

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**", "/favicon/**");
    }

    
    
}