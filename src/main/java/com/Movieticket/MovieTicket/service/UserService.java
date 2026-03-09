package com.Movieticket.MovieTicket.service;

import com.Movieticket.MovieTicket.Config.JWTutils;
import com.Movieticket.MovieTicket.dto.UserRequest;
import com.Movieticket.MovieTicket.model.User;
import com.Movieticket.MovieTicket.repo.UserRepo;
import com.Movieticket.MovieTicket.util.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {


    private UserRepo repo;

    private AuthenticationManager authenticationManager;
    private JWTutils jwTutils;

    public UserService(UserRepo repo, AuthenticationManager authenticationManager , JWTutils jwTutils) {
        this.repo = repo;
        this.authenticationManager = authenticationManager;
        this.jwTutils= jwTutils;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public UserRequest register(UserRequest userRequest) {
        User user = UserMapper.userDtoTouser(userRequest);
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return UserMapper.usertoDTO(user);
    }

    public String verify(UserRequest usersRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usersRequest.getEmail(),usersRequest.getPassword())
            );
            if (auth.isAuthenticated()){
                return jwTutils.generateToken(usersRequest.getEmail()) ;
            }else return "fail";

        }catch (Exception e){
           return String.valueOf(new RuntimeException("Some exception"));
        }
    }
}
