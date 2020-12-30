package com.zzc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Auther xiao_kai
 * @Date 2020/12/30 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteBook {
    private String cn_notebook_id;
    private String cn_user_id;
    private String cn_notebook_type_id;
    private String cn_notebook_name;
    private String cn_notebook_desc;
    private Timestamp cn_notebook_createtime;
}
