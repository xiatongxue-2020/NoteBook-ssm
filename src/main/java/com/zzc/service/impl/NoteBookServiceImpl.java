package com.zzc.service.impl;

import com.zzc.dao.NoteBookDao;
import com.zzc.entity.JsonInfo;
import com.zzc.entity.NoteBook;
import com.zzc.service.NoteBookService;
import com.zzc.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Auther xiao_kai
 * @Date 2020/12/30 16:12
 */
@Service
public class NoteBookServiceImpl implements NoteBookService {

    @Resource
    private NoteBookDao noteBookDao;
    @Override
    public JsonInfo findById(String userId) {
        List<NoteBook> books = noteBookDao.findById(userId);
        JsonInfo jsonInfo = new JsonInfo();
        jsonInfo.setStatus(0);
        jsonInfo.setMsg("ok");
        jsonInfo.setData(books);
        return jsonInfo;
    }

    @Override
    public JsonInfo addBook(String bookName, String userid) {
        JsonInfo jsonInfo = new JsonInfo();
        NoteBook noteBook = new NoteBook();
        noteBook.setCn_user_id(userid);
        noteBook.setCn_notebook_id(NoteUtil.getUUID());
        noteBook.setCn_notebook_name(bookName);
        noteBook.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
        noteBook.setCn_notebook_desc("");
        noteBook.setCn_notebook_type_id("5");

        int i = noteBookDao.addBook(noteBook);
        if (i >0){
            jsonInfo.setStatus(0);
            jsonInfo.setMsg("ok");
            jsonInfo.setData(noteBook);
        }
        return jsonInfo;
    }

    @Override
    public JsonInfo rename(String bookid, String bookname) {
        JsonInfo jsonInfo = new JsonInfo();
        int i = noteBookDao.updateName(bookid,bookname);
        if (i>0){
            jsonInfo.setMsg("ok");
            jsonInfo.setStatus(0);
            return jsonInfo;
        }
        jsonInfo.setStatus(1);
        jsonInfo.setMsg("error");
        return jsonInfo;
    }
}
