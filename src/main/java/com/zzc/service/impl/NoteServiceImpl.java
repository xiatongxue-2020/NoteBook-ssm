package com.zzc.service.impl;

import com.zzc.dao.NoteDao;
import com.zzc.dao.ShareDao;
import com.zzc.entity.JsonInfo;
import com.zzc.entity.Note;
import com.zzc.entity.Share;
import com.zzc.service.NoteService;
import com.zzc.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author EDZ
 * @Auther xiao_kai
 * @Date 2020/12/31 14:10
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Resource
    private NoteDao noteDao;
    @Resource
    private ShareDao shareDao;
    @Override
    public JsonInfo findById(String bookId) {
        List<Note> notes = noteDao.findById(bookId);
        JsonInfo jsonInfo = new JsonInfo();
        jsonInfo.setStatus(0);
        jsonInfo.setMsg("ok");
        jsonInfo.setData(notes);
        return jsonInfo;
    }

    @Override
    public JsonInfo findByNoteId(String noteid) {
        Note note = noteDao.findByNoteId(noteid);
        JsonInfo jsonInfo = new JsonInfo();
        jsonInfo.setStatus(0);
        jsonInfo.setMsg("ok");
        jsonInfo.setData(note);
        return jsonInfo;
    }

    @Override
    public JsonInfo saveNote(String noteid, String title, String body) {
        Note note = Note.builder().cn_note_id(noteid).cn_note_title(title).cn_note_body(body).build();

        noteDao.dynamicUpdate(note);
        JsonInfo jsonInfo = new JsonInfo();
        jsonInfo.setStatus(0);
        jsonInfo.setMsg("ok");
        return jsonInfo;
    }

    @Override
    public JsonInfo addNote(String userid, String bookid, String noteTitle) {
        JsonInfo jsonInfo = new JsonInfo();
        Note note = new Note();
        note.setCn_note_id(NoteUtil.getUUID());
        note.setCn_note_title(noteTitle);
        note.setCn_user_id(userid);
        note.setCn_notebook_id(bookid);
        note.setCn_note_create_time(System.currentTimeMillis()+"");
        note.setCn_note_body("");
        note.setCn_note_status_id("1");
        noteDao.addNote(note);


        jsonInfo.setStatus(0);
        jsonInfo.setMsg("ok");
        jsonInfo.setData(note);

        return jsonInfo;
    }

    @Override
    public JsonInfo deleteNote(String noteid) {
        JsonInfo jsonInfo = new JsonInfo();
        Note note = Note.builder().cn_note_id(noteid).cn_note_status_id("2").build();
        int i = noteDao.dynamicUpdate(note);
        if (i>0){
            jsonInfo.setStatus(0);
            jsonInfo.setMsg("ok");
            return jsonInfo;
        }
        jsonInfo.setStatus(1);
        jsonInfo.setMsg("删除笔记失败");
        return jsonInfo;
    }

    @Override
    public JsonInfo findByUserId(String userid) {
        JsonInfo jsonInfo = new JsonInfo();
        List<Note> notes= noteDao.findByUserId(userid);
        jsonInfo.setStatus(0);
        jsonInfo.setMsg("ok");
        jsonInfo.setData(notes);
        return jsonInfo;
    }

    @Override
    public JsonInfo moveNote(String noteid, String bookid) {
        JsonInfo jsonInfo = new JsonInfo();
        Note note = Note.builder().cn_note_id(noteid).cn_notebook_id(bookid).build();
        int i= noteDao.dynamicUpdate(note);
        if (i>0){
            jsonInfo.setStatus(0);
            jsonInfo.setMsg("ok");
            return jsonInfo;
        }
        jsonInfo.setStatus(1);
        jsonInfo.setMsg("error");
        return jsonInfo;
    }
    @Transactional
    @Override
    public JsonInfo shareNote(String noteid) {
        JsonInfo jsonInfo = new JsonInfo();
        //1.查询笔记是否被修改过
        Note note = noteDao.findByNoteId(noteid);
        if(note.getCn_note_type_id().equals("2")){
            jsonInfo.setStatus(1);
            jsonInfo.setMsg("该笔记已经被分享过了");
            return jsonInfo;
        }
        //2.修改笔记状态 type_id = 2
        Note build = Note.builder().cn_note_id(noteid).cn_note_type_id("2").build();
        int i = noteDao.dynamicUpdate(build);
        if (i>0){
            jsonInfo.setStatus(0);
            jsonInfo.setMsg("笔记分享ok");
        //3.分享过的笔记插入share表中
        Share share = new Share();
        share.setCn_note_id(note.getCn_note_id());
        share.setCn_share_body(note.getCn_note_body());
        share.setCn_share_id(NoteUtil.getUUID());
        share.setCn_share_title(note.getCn_note_title());
        shareDao.insert(share);
        return jsonInfo;
        }
        jsonInfo.setStatus(3);
        jsonInfo.setMsg("笔记分享失败");
        return jsonInfo;
    }

    @Override
    public JsonInfo replayNote(String noteid, String bookid) {
        JsonInfo jsonInfo = new JsonInfo();
        //修改状态cn_note_status_id =1
        //修改笔记本ID
        Note note = Note.builder().cn_note_id(noteid).cn_notebook_id(bookid).cn_note_status_id("1").build();

        int i = noteDao.dynamicUpdate(note);
        if (i>0){
            jsonInfo.setStatus(0);
            jsonInfo.setMsg("ok");
            return jsonInfo;
        }
        jsonInfo.setStatus(1);
        jsonInfo.setMsg("error");
        return jsonInfo;
    }

    @Override
    public JsonInfo deleteRollbackNote(String noteid) {
        JsonInfo jsonInfo = new JsonInfo();
       int i = noteDao.deleteNote(noteid);
       if (i>0){
           jsonInfo.setStatus(0);
           jsonInfo.setMsg("删除笔记成功");
           return jsonInfo;
       }
       jsonInfo.setStatus(1);
       jsonInfo.setMsg("error");
        return jsonInfo;
    }

    @Override
    public JsonInfo searchNote(String keyword, Integer page) {
        JsonInfo jsonInfo = new JsonInfo();
        int start = (page -1)*5;
        keyword = "%" + keyword + "%";
        List<Share> shares = shareDao.findLikeTitle(keyword,start);
        jsonInfo.setStatus(0);
        jsonInfo.setMsg("ok");
        jsonInfo.setData(shares);
        return jsonInfo;
    }

    @Override
    public JsonInfo findByShareId(String shareid) {
        JsonInfo jsonInfo = new JsonInfo();
        Share share = shareDao.findById(shareid);
        jsonInfo.setStatus(0);
        jsonInfo.setMsg("ok");
        jsonInfo.setData(share);
        return jsonInfo;
    }
}
