package com.zzc.dao;

import com.zzc.entity.Note;

import java.util.List;

/**
 * @author xiao_kai
 */
public interface NoteDao {
    /**
     * 根据笔记本的ID找到对应的笔记
     * @param bookId
     * @return
     */
    List<Note> findById(String bookId);

    /**
     * 根据笔记ID查看笔记详情
     * @param noteid
     * @return
     */
    Note findByNoteId(String noteid);

    /**
     * 添加笔记
     * @param note
     */
    void addNote(Note note);
    /**
     * 根据用户ID查询笔记信息
     * @param userid
     * @return
     */
    List<Note> findByUserId(String userid);
    /**
     * 动态修改信息
     * @param note
     * @return
     */
    int dynamicUpdate(Note note);

    int deleteNote(String noteid);
}
