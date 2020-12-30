package com.zzc.service.impl;

import com.zzc.dao.NoteBookDao;
import com.zzc.entity.JsonInfo;
import com.zzc.entity.NoteBook;
import com.zzc.service.NoteBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
