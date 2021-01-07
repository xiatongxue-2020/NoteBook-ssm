package com.zzc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xiao_kai
 * @Date 2021/1/6 16:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Share {
    private String cn_share_id;
    private String cn_share_title;
    private String cn_share_body;
    private String cn_note_id;
}
