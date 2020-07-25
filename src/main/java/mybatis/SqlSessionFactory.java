package mybatis;

/**
 * Classname:mybatis-self
 *
 * @description:SqlSession的工厂接口
 * @author: 陌意随影
 * @Date: 2020-07-25 15:12
 */
public interface SqlSessionFactory {
    /**
     * @Description :获取一个SQLSession对象
     * @Date  2020/7/26 0026
     * @Param * @param  ：
     * @return mybatis.SqlSession
     **/
    SqlSession openSession();
}
