package dao;
import entity.User;
import mybatis.Resources;
import mybatis.SqlSession;
import mybatis.SqlSessionFactory;
import mybatis.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
/**
 * Classname:UserDaoTest
 *
 * @description:测试Userdao
 * @author: 陌意随影
 * @Date: 2020-07-24 10:49
 * @Version: 1.0
 **/
public class UserDaoTest {
    @Test
    public void testGetUserAll(){
        try {
            //读取配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //实例化SqlSessionFactoryBuilder
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
          //构建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
           //产生一个SqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //利用SqlSession创建UserDAao的代理对象
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            //利用代理对象执行方法
            List<User> userList = userDao.getAll();
            for (User u:userList ) {
                System.out.println(u);
            }
            User user = userDao.getOne(1);
            System.out.println(user);
            //关闭资源
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
