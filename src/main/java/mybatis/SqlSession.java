package mybatis;

import dao.UserDao;

/**
 * Classname:mybatis-self
 * @description:{description}
 * @author: 陌意随影
 * @Date: 2020-07-25 15:12
 */
public interface SqlSession {
    /**
     * @Description :获取参数的代理对象
     * @Date 15:26 2020/7/25 0025
     * @Param * @param daoClass ：
     * @return T
     **/
    <T> T getMapper(Class<T> daoClass);
  /**
   * @Description :关闭资源
   * @Date 15:27 2020/7/25 0025
   * @Param * @param  ：
   * @return void
   **/
    void close();
}
