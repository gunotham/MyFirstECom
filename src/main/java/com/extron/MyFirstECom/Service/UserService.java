package com.extron.MyFirstECom.Service;

import com.extron.MyFirstECom.Model.Cart;
import com.extron.MyFirstECom.Model.Users;
import com.extron.MyFirstECom.Repository.CartRepo;
import com.extron.MyFirstECom.Repository.UserRepo;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserService {


    private final UserRepo repo;
    private final AuthenticationManager authManager;
    private final JwtService service;
    private final CartRepo cartRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepo repo, AuthenticationManager authManager, JwtService service, CartRepo cartRepo) {
        this.repo = repo;
        this.authManager = authManager;
        this.service = service;
        this.cartRepo = cartRepo;
    }

    public String verifyUser(Users user) {
        Authentication authenticate = authManager.authenticate(new
                UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authenticate.isAuthenticated()) return service.generateToken(user.getUsername());
        return "Login failed.";
    }

    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Users savedUser = repo.save(user);
        
        Cart cart = new Cart();
        cart.setUser(savedUser);
        cart.setCreatedAt(new Date());
        cartRepo.save(cart);
        
        return savedUser;
    }
}
