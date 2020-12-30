package com.zzc.service;

import com.zzc.entity.JsonInfo;
import com.zzc.entity.User;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * @Auther xiao_kai
 * @Date 2020/12/29 15:43
 */

public interface UserService {
    JsonInfo findByName(String name,String password) throws NoSuchAlgorithmException;
    JsonInfo insert(User user);
}
