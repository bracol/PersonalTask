package com.example.waniltonfilho.personaltasks.model.service;

import com.example.waniltonfilho.personaltasks.model.entities.Login;
import com.example.waniltonfilho.personaltasks.model.persistance.login.LoginRepository;

import java.util.List;

/**
 * Created by wanilton.filho on 20/01/2016.
 */
public class LoginBusinessService {

    private LoginBusinessService(){super();}

    public static List<Login> findAll(){
        return LoginRepository.getAll();
    }

    public static void save(Login login){
        LoginRepository.save(login);
    }

    public static void delete(Login selectedLogin){
        LoginRepository.delete(selectedLogin.getId());
    }

}
