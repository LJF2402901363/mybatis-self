package dao;

import entity.User;

import java.util.List;

/**
 * Classname:mybatisdemo
 * @description:User的dao接口
 * @author: 陌意随影
 * @Date: 2020-07-24 10:35
 */
public interface UserDao {
    /**
     * @date: 2020/7/24 0024 10:41
     * @description:获取所有的用户信息
     * @return: 返回包含所有的用户的list
     */
    List<User> getAll();
    /**
     * @Description :根据id获取制定对象
     * @Date 0:32 2020/7/26 0026
     * @Param * @param id ：
     * @return entity.User
     **/
   User getOne(int id);

}