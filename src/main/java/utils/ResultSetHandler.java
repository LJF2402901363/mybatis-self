package utils;

import java.sql.ResultSet;
import java.util.List;

/**
 * Classname:mybatis-self
 *
 * @description:结果集
 * @author: 陌意随影
 * @Date: 2020-07-25 20:31
 */
public interface ResultSetHandler<T> {
    public  List<T> resultSet(ResultSet resultSet,String resultTypePath) throws Exception;
}
