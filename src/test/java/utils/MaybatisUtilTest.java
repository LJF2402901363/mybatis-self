package utils;

import entity.User;
import mybatis.Resources;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * Classname:MaybatisUtilTest
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2020-07-25 16:04
 * @Version: 1.0
 **/
public class MaybatisUtilTest {
    @Test
    public  void testLoadConfigurantion(){
        MybatisUtil.LoaderConfiguration(Resources.getResourceAsStream("mybatis-config.xml"));
    }
    @Test
    public void  testUser(){
        User user = new User();
        Class<? extends User> aClass = user.getClass();
        Method[] methods = aClass.getMethods();
        for (Method m : methods) {
            if (m.getName().startsWith("set")){
                String name = m.getParameterTypes()[0].getName();
             System.out.println(name);
            }

        }
    }
}
