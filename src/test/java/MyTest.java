import com.zzc.dao.NoteDao;
import com.zzc.dao.ShareDao;
import com.zzc.dao.UserDao;
import com.zzc.entity.JsonInfo;
import com.zzc.entity.Note;
import com.zzc.entity.Share;
import com.zzc.service.impl.NoteServiceImpl;
import com.zzc.util.NoteUtil;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Auther xiao_kai
 * @Date 2020/12/29 15:37
 */
public class MyTest {
    @Test
    public void test01(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("Spring.xml");
        NoteDao noteDao = ac.getBean("noteDao", NoteDao.class);
        Note note = Note.builder().cn_note_id(NoteUtil.getUUID()).cn_notebook_id("44c6843b95f34f4bb1e6c115915b2457").cn_note_title("夏彦恺").cn_user_id("333c6d0b-e4a2-4596-9902-a5d98c2f665a").build();
//        noteDao.addNote(note);
        List<Note> byId = noteDao.findByUserId("333c6d0b-e4a2-4596-9902-a5d98c2f665a");
        System.out.println(byId);
    }
    @Test
    public void test02(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("Spring.xml");
        NoteServiceImpl noteServiceImpl = ac.getBean("noteServiceImpl", NoteServiceImpl.class);
        JsonInfo jsonInfo = noteServiceImpl.searchNote("", 1);
        System.out.println(jsonInfo.getData());


    }
}
