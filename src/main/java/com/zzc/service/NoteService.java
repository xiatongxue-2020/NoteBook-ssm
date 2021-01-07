package com.zzc.service;

import com.zzc.entity.JsonInfo;

public interface NoteService {
    JsonInfo findById(String bookId);
    JsonInfo findByNoteId(String noteid);
    JsonInfo saveNote(String noteid, String title, String body);
    JsonInfo addNote(String userid, String bookid, String noteTitle);

    JsonInfo deleteNote(String noteid);

    JsonInfo findByUserId(String userid);

    JsonInfo moveNote(String noteid, String bookid);

    JsonInfo shareNote(String noteid);

    JsonInfo replayNote(String noteid, String bookid);

    JsonInfo deleteRollbackNote(String noteid);

    JsonInfo searchNote(String keyword, Integer page);

    JsonInfo findByShareId(String shareid);
}
