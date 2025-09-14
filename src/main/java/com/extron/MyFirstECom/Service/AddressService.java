package com.extron.MyFirstECom.Service;


import com.extron.MyFirstECom.DTO.AddressDTO;
import com.extron.MyFirstECom.Model.Address;
import com.extron.MyFirstECom.Model.Users;
import com.extron.MyFirstECom.Repository.AddressRepo;
import com.extron.MyFirstECom.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AddressService {
    
    final private UserRepo userRepo;
    final private AddressRepo addressRepo;
    
    public AddressService(UserRepo userRepo, AddressRepo addressRepo){
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
    }
    
    public List<Address> getUserAddress(Long userId) {

        Users user = userRepo.findById(userId).orElse(null);
        if(user == null) {
            return new ArrayList<>();
        }
        return user.getAddresses();
    }

    public String addUserAddress(Long userId, AddressDTO addressInfo) {
        Users user = userRepo.findById(userId).orElse(null);
        Address newAddress = new Address();
        
        newAddress.setStreet(addressInfo.getStreet());
        newAddress.setCity(addressInfo.getCity());
        newAddress.setState(addressInfo.getState());
        newAddress.setPinCode(addressInfo.getPinCode());
        newAddress.setCountry(addressInfo.getCountry());
        newAddress.setUser(user);
        addressRepo.save(newAddress);
        
        return "Added new address";
    }

    public String patchUpdateAddress(Long userId, AddressDTO addressInfo) {
        
        Address updatingAddress = addressRepo.findById(addressInfo.getAddressId()).orElse(null);
        
        if(updatingAddress == null || updatingAddress.getUser().getUserId() != userId){
            return "Error: Address not found or does not belong to the user!";
        }
        
        if(!Objects.equals(updatingAddress.getStreet(), addressInfo.getStreet()) && addressInfo.getStreet()!=null)
            updatingAddress.setStreet(addressInfo.getStreet());
            
        if(!Objects.equals(updatingAddress.getCity(), addressInfo.getCity()) && addressInfo.getCity()!=null) 
            updatingAddress.setCity(addressInfo.getCity());

        if(!Objects.equals(updatingAddress.getCountry(), addressInfo.getCountry()) && addressInfo.getCountry()!=null)
            updatingAddress.setCountry(addressInfo.getCountry());

        if(!Objects.equals(updatingAddress.getState(), addressInfo.getState()) && addressInfo.getState()!=null)
            updatingAddress.setState(addressInfo.getState());

        if(!Objects.equals(updatingAddress.getPinCode(), addressInfo.getPinCode()) && addressInfo.getPinCode()!=null)
            updatingAddress.setPinCode(addressInfo.getPinCode());
            
        addressRepo.save(updatingAddress);
        return "Successfully updated address";
        
    }


    public String deleteAddress(Long addressId) {
        addressRepo.deleteById(addressId);
        return "Deleted the address";
    }
}
