package com.extron.MyFirstECom.Repository;

import com.extron.MyFirstECom.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
