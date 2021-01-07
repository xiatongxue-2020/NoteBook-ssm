package com.zzc.dao;

import com.zzc.entity.NoteBook;

import java.util.List;

/**
 * @author EDZ
 */
public interface NoteBookDao {
    /**
     * 根据用户ID查询用户的笔记本
     * @param id
     * @return
     */
    List<NoteBook> findById(String id);

    int addBook(NoteBook noteBook);

    int updateName(String bookid, String bookname);
}
