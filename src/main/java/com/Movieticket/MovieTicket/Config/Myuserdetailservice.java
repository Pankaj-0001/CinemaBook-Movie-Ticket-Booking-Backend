package com.Movieticket.MovieTicket.Config;

import com.Movieticket.MovieTicket.model.User;
import com.Movieticket.MovieTicket.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Component
public class Myuserdetailservice implements UserDetailsService {


    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userinfo = repo.findByEmail(username);
        if (userinfo.isPresent()){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userinfo.get().getUsername())
                    .password(userinfo.get().getPassword())
                    .roles(String.valueOf(Collections.singleton(new SimpleGrantedAuthority("USER"))))
                    .build();
        }else {
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
    }
}
