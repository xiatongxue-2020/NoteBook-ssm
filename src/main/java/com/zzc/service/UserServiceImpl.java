package com.zzc.service;

import com.zzc.dao.UserDao;
import com.zzc.entity.JsonInfo;
import com.zzc.entity.User;
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
        //密码不正确
        String s = NoteUtil.md5(password);
        if(!user.getCu_user_password().equals(s)){
            jsonInfo.setStatus(2);
            jsonInfo.setMsg("密码或账户错误");
            return jsonInfo;
        }
        jsonInfo.setStatus(0);
        jsonInfo.setMsg("成功登陆");
        //屏蔽密码操作
        user.setCu_user_password("");
        jsonInfo.setData(user);
        return jsonInfo;
    }
}
