package com.zzc.controller;

import com.zzc.entity.JsonInfo;
import com.zzc.service.NoteBookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther xiao_kai
 * @Date 2020/12/30 15:58
 */
@RestController
public class LoadBookController {
    @Resource
    private NoteBookService noteBookService;
    @RequestMapping("/book/loadbooks.do")
    public JsonInfo findById(String userId){
        JsonInfo jsonInfo = noteBookService.findById(userId);
        return jsonInfo;
    }
    @RequestMapping("/book/add.do")
    public JsonInfo addBook(String bookName,String userid){
       JsonInfo jsonInfo =  noteBookService.addBook(bookName,userid);
        return jsonInfo;
    }
    @RequestMapping("/book/rename.do")
    public JsonInfo renameBook(String bookid,String bookname){
        JsonInfo jsonInfo =  noteBookService.rename(bookid,bookname);
        return jsonInfo;
    }

}
