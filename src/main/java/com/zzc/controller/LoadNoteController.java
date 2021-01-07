package com.zzc.controller;

import com.zzc.entity.JsonInfo;
import com.zzc.service.NoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther xiao_kai
 * @Date 2020/12/31 14:45
 */
@RestController
public class LoadNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/loadNotes.do")
    public JsonInfo findById(String bookid){
        JsonInfo jsonInfo = noteService.findById(bookid);
        return jsonInfo;
    }
    /**
     * 根据笔记ID查询笔记信息
     */
    @RequestMapping("/note/noteInfo.do")
    public JsonInfo noteInfo(String noteid){
        return noteService.findByNoteId(noteid);
    }

    /**
     * 保存笔记信息
     * @param noteid
     * @param title
     * @param body
     * @return
     */
    @RequestMapping("/note/savenote.do")
    public JsonInfo savenote(String noteid,String title,String body){
        return noteService.saveNote(noteid,title,body);
    }

    /**
     * 添加笔记
     * @return
     */
    @RequestMapping("/note/add.do")
    public JsonInfo addNote(String userid,String bookid,String noteTitle){
        return noteService.addNote(userid,bookid,noteTitle);
    }

    /**
     * 删除笔记
     * @param noteid
     * @return
     */
    @RequestMapping("/note/delete.do")
    public JsonInfo delete(String noteid){
        return noteService.deleteNote(noteid);
    }
    /**
     * 显示回收站里面的日记
     */
    @RequestMapping("/note/recycleNote.do")
    public JsonInfo findOldNote(String userid){
        return noteService.findByUserId(userid);
    }

    /**
     * 移动笔记
     * @param noteid
     * @param bookid
     * @return
     */
    @RequestMapping("/note/move.do")
    public JsonInfo moveNote(String noteid,String bookid){
        return noteService.moveNote(noteid,bookid);
    }
    /**
     * 分享笔记
     * @param noteid
     * @return
     */
    @RequestMapping("/note/shareNote.do")
    public JsonInfo shareNote(String noteid){
        return noteService.shareNote(noteid);
    }

    /**
     * 恢复回收站里面的笔记
     * @param noteid
     * @param bookid
     * @return
     */
    @RequestMapping("/note/replayNote.do")
    public JsonInfo replayNote(String noteid,String bookid){
        return noteService.replayNote(noteid,bookid);
    }

    /**
     * 彻底删除笔记
     * @param noteid
     * @return
     */
    @RequestMapping("/note/deleteRollbackNote.do")
    public JsonInfo deleteNote(String noteid){
        return noteService.deleteRollbackNote(noteid);
    }

    /**
     *
     * @param keyword
     * @param page
     * @return
     */
    @RequestMapping("/note/search_note.do")
    public JsonInfo searchNote(String keyword,Integer page){
        return noteService.searchNote(keyword,page);
    }
    @RequestMapping("/note/shareInfo.do")
    public JsonInfo shareInfo(String shareid){
        return noteService.findByShareId(shareid);
    }
}
