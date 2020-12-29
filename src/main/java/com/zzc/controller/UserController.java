package com.zzc.controller;

import com.zzc.entity.JsonInfo;
import com.zzc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther xiao_kai
 * @Date 2020/12/29 15:44
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/user/login.do")
    public JsonInfo login(String username,String password) throws NoSuchAlgorithmException {
        JsonInfo jsonInfo = userService.findByName(username, password);
        return jsonInfo;
    }

}
