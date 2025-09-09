package com.extron.MyFirstECom.Repository;

import com.extron.MyFirstECom.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
