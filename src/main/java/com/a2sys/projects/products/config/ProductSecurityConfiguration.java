package com.a2sys.projects.products.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ProductSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*
        auth.inMemoryAuthentication()
                .passwordEncoder(a2sysPasswordEncoder())
                .withUser("a2sys")
                .password(a2sysPasswordEncoder().encode("spring"))
                .roles("ADMIN", "CONSULTANT", "USER")
                ;
*/

        auth.userDetailsService(new A2SYSUserManagement());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(
                        PathRequest
                        .toStaticResources()
                        .atCommonLocations()).permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(
                            new AntPathRequestMatcher("/logout")
                    ).logoutSuccessUrl("/login");
    }

    private PasswordEncoder a2sysPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
