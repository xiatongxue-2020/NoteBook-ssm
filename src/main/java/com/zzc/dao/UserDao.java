package com.zzc.dao;

import com.zzc.entity.User;

import java.util.List;

/**
 * @Auther xiao_kai
 * @Date 2020/12/29 15:35
 */
public interface UserDao {
    List<User> findAll();
}
