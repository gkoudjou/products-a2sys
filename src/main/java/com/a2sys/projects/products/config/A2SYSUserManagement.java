package com.a2sys.projects.products.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A2SYSUserManagement implements UserDetailsService {

    private final Map<String, String> credentials = new HashMap<>();

    public A2SYSUserManagement() {
        credentials.put("kizito", "azerty");
        credentials.put("isaka", "102erf");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(credentials.get(username) != null) {
            String password = credentials.get(username);
            return User.builder()
                    .username(username)
                    .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                    .accountLocked(false)
                    .accountExpired(false)
                    .roles("ADMIN", "USER")
                    .build();
        }
        throw new UsernameNotFoundException(username);
    }
}


