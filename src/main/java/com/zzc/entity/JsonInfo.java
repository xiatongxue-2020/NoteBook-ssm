package com.zzc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther xiao_kai
 * @Date 2020/12/29 15:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JsonInfo {
    private Integer status;
    private String msg;
    private Object data;
}
