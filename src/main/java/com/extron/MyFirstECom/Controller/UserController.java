package com.extron.MyFirstECom.Controller;

import com.extron.MyFirstECom.Model.Users;
import com.extron.MyFirstECom.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user){
        Users newUser = service.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody Users user){
        return new ResponseEntity<>(service.verifyUser(user), HttpStatus.OK);
    }
}
