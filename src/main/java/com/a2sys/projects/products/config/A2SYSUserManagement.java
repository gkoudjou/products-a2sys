package com.a2sys.projects.products.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

public class A2SYSUserManagement implements UserDetailsService {
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        throw new UsernameNotFoundException(username);
    }
}


