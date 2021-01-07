package com.zzc.service.impl;

import com.zzc.dao.UserDao;
import com.zzc.entity.JsonInfo;
import com.zzc.entity.User;
import com.zzc.service.UserService;
import com.zzc.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * @author EDZ
 * @Auther xiao_kai
 * @Date 2020/12/29 16:05
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public JsonInfo findByName(String name,String password) throws NoSuchAlgorithmException {
        User user = userDao.findByName(name);
        JsonInfo jsonInfo = new JsonInfo();
        if (user == null){
            jsonInfo.setStatus(1);
            jsonInfo.setMsg("用户名不存在");
            return jsonInfo;
        }
        //把你传过来的密码转换为密文
        String s = NoteUtil.md5(password);
        //通过密文进行判断
        if(!user.getCn_user_password().equals(s)){
            jsonInfo.setStatus(2);
            jsonInfo.setMsg("密码或账户错误");
            return jsonInfo;
        }
        jsonInfo.setStatus(0);
        jsonInfo.setMsg("成功登陆");
        //屏蔽密码操作
        user.setCn_user_password("");
        jsonInfo.setData(user);
        return jsonInfo;
    }

    @Override
    public JsonInfo insert(User user) {
        //验证用户名是否可用
        User username = userDao.findByName(user.getCn_user_name());
        JsonInfo jsonInfo = new JsonInfo();
        if (username != null){
            //说明用户名已被注册
            jsonInfo.setStatus(1);
            jsonInfo.setMsg("该用户名已被注册");
            return jsonInfo;
        }else {
            //用户名可用执行注册
            userDao.insert(user);
            jsonInfo.setStatus(0);
            jsonInfo.setMsg("注册成功");
            return jsonInfo;
        }
    }
}
