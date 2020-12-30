package com.zzc.controller;

import com.zzc.entity.JsonInfo;
import com.zzc.entity.User;
import com.zzc.service.UserService;
import com.zzc.util.NoteUtil;
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
    @RequestMapping("/user/regist.do")
    public JsonInfo regist(String username,String nick,String password) throws NoSuchAlgorithmException {
        String uuid = NoteUtil.getUUID();
        String s = NoteUtil.md5(password);
        JsonInfo jsonInfo = userService.insert(User.builder().cn_user_name(username).cn_user_nick(nick).cn_user_password(s).cn_user_id(uuid).build());
        return jsonInfo;
    }
}
