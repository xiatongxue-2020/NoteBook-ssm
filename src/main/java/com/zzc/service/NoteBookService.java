package com.zzc.service;

import com.zzc.entity.JsonInfo;

public interface NoteBookService {
    JsonInfo findById(String userId);

    JsonInfo addBook(String bookName,String userid);

    JsonInfo rename(String bookid, String bookname);
}
