package com.extron.MyFirstECom.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class JWTService {

    private String secretKey="";

    public JWTService() throws NoSuchAlgorithmException {
        KeyGenerator keyGen =KeyGenerator.getInstance("HmacSHA256");
    }
}
