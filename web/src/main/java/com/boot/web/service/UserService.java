package com.boot.web.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserById(Integer id){
        System.out.println("get ....");
        return "user";
    }

     public void deleteById(Integer id){
         System.out.println("delete ...");
     }
}