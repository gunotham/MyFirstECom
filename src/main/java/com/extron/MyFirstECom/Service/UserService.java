package com.extron.MyFirstECom.Service;

import com.extron.MyFirstECom.Model.Users;
import com.extron.MyFirstECom.Repository.UserRepo;
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

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepo repo, AuthenticationManager authManager) {
        this.repo = repo;
        this.authManager = authManager;
    }

    public String verifyUser(Users user) {
        Authentication authenticate = authManager.authenticate(new
                UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authenticate.isAuthenticated()) return "Login Successful";
        return "Login failed.";
    }

    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
