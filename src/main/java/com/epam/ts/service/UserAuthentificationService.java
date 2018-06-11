package com.epam.ts.service;

import com.epam.ts.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthentificationService implements UserDetailsService {
@Autowired
TicketService ticketService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        User user = ticketService.getUser(username);
        builder = org.springframework.security.core.userdetails.User.withUsername(user.getName());
        builder.password(user.getPassword());
        builder.roles(user.getRoles().split(","));
        return builder.build();
    }
}
