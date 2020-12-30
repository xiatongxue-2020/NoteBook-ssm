package com.zzc.util;

import com.alibaba.druid.util.Base64;
import com.sun.org.apache.regexp.internal.RE;

import java.security.BasicPermission;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * @Auther xiao_kai
 * @Date 2020/12/29 16:46
 */
public class NoteUtil {
    /**
     * 1.生成主键UUID
     * 2.将密码的明文经过md5散列
     */
   public static String getUUID(){
       UUID uuid = UUID.randomUUID();
       String uuidstr = uuid.toString().replace("-", "");

       return uuidstr;
   }
   //2.明文经过MD5加密
    public static String md5(String pwd) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(pwd.getBytes());
        String s = Base64.byteArrayToAltBase64(digest);
        return s;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(getUUID());
        System.out.println(md5("123456"));
    }

}
