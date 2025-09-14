package com.extron.MyFirstECom.Controller;


import com.extron.MyFirstECom.DTO.AddressDTO;
import com.extron.MyFirstECom.Model.Address;
import com.extron.MyFirstECom.Service.AddressService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    
    @Autowired
    private AddressService addressService;
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<Address>> getAddress(@PathVariable Long userId){
        return new ResponseEntity<>(addressService.getUserAddress(userId), HttpStatus.OK);
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<String> addAddress(@PathVariable Long userId, @RequestBody AddressDTO addressInfo){
        return new ResponseEntity<>(addressService.addUserAddress(userId, addressInfo), HttpStatus.OK);
    }
    
    @PatchMapping("/{userId}")
    public ResponseEntity<String> patchUpdateAddress(@PathVariable Long userId, @RequestBody AddressDTO addressInfo){
        return new ResponseEntity<>(addressService.patchUpdateAddress(userId, addressInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId){
        return new ResponseEntity<>(addressService.deleteAddress(addressId), HttpStatus.OK);
    }
}
