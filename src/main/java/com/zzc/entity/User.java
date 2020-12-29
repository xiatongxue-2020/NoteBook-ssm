package com.zzc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther xiao_kai
 * @Date 2020/12/29 15:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String cn_user_id;
    private String cn_user_name;
    private String cu_user_password;
    private String cn_user_token;
    private String cn_user_nick;
}
