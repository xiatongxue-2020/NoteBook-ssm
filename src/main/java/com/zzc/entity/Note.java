package com.zzc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther xiao_kai
 * @Date 2020/12/31 13:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {
    private String cn_note_id;
    private String cn_notebook_id;
    private String cn_user_id;
    private String cn_note_status_id;
    private String cn_note_type_id;
    private String cn_note_title;
    private String cn_note_body;
    private String cn_note_create_time;
    private String cn_note_last_modify_time;
}
