import com.zzc.dao.UserDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther xiao_kai
 * @Date 2020/12/29 15:37
 */
public class MyTest {
    @Test
    public void test01(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("Spring.xml");
        UserDao userDao = ac.getBean("userDao", UserDao.class);
        System.out.println(userDao.findByName("zz1"));
    }
}
