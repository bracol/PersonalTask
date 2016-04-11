package com.example.waniltonfilho.personaltasks.model.service;

import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.persistance.login.LoginRepository;

import java.util.List;

/**
 * Created by wanilton.filho on 20/01/2016.
 */
public class LoginBusinessService {

    private LoginBusinessService(){super();}

    public static List<User> findAll(){
        return LoginRepository.getAll();
    }

    public static void save(User user){
        LoginRepository.save(user);
    }

    public static void delete(User selectedUser){
        LoginRepository.delete(selectedUser.getId());
    }

}
