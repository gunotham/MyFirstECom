package com.extron.MyFirstECom.Controller;

import com.extron.MyFirstECom.Model.Users;
import com.extron.MyFirstECom.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

  @Autowired
  private UserService service;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody Users user) {
    return service.registerUser(user);
  }

  @PostMapping("/api/login")
  public ResponseEntity<String> userLogin(@RequestBody Users user) {
    return new ResponseEntity<>(service.verifyUser(user), HttpStatus.OK);
  }
}
