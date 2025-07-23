package com.extron.MyFirstECom.Service;

import com.extron.MyFirstECom.Model.Users;
import com.extron.MyFirstECom.Repository.UserRepo;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepo repo;
    private final AuthenticationManager authManager;
    private final JwtService service;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepo repo, AuthenticationManager authManager, JwtService service) {
        this.repo = repo;
        this.authManager = authManager;
        this.service = service;
    }

    public String verifyUser(Users user) {
        Authentication authenticate = authManager.authenticate(new
                UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authenticate.isAuthenticated()) return service.generateToken(user.getUsername());
        return "Login failed.";
    }

    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
