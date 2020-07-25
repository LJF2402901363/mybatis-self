package utils;

import mybatis.Configurantion;
import mybatis.Mapper;

import java.sql.*;
import java.util.List;

/**
 * Classname:MyBatisJDBCUtil
 *
 * @description:获取sql连接的工具类
 * @author: 陌意随影
 * @Date: 2020-07-25 18:17
 * @Version: 1.0
 **/
public class MyBatisJDBCUtil {
    public static Connection getConnection(Configurantion configurantion) {
        try {
            //加载驱动
            Class.forName(configurantion.getDriver());
            try {
                //获取连接
                Connection connection = DriverManager.getConnection(configurantion.getUrl(), configurantion.getUsername(), configurantion.getPassword());
                return  connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }
  public  static <T> List<T> query(Mapper mapper, Connection connection, ResultSetHandler resultSetHandler, Object[] param) throws Exception {
      //设置禁止自动提交
        connection.setAutoCommit(false);
      PreparedStatement preparedStatement =  null;
      List<T> t = null;
      try {
          //获取预处理对象
          preparedStatement =  connection.prepareStatement(mapper.getSql());
          //设置占位参数
          if (param != null && param.length != 0){
              for (int i = 0; i < param.length;i++){
                  preparedStatement.setObject(i+1,param[i]);
              }
          }
          //获取结果集
          ResultSet resultSet = preparedStatement.executeQuery();
          //对结果集进行处理
          t = resultSetHandler.resultSet(resultSet,mapper.getResultTypePath());
      }catch (Exception e){
          e.printStackTrace();
      }finally {
          //设置允许自动提交
          connection.setAutoCommit(true);
          //返回处理结果
          return  t;
      }



  }
    public static int update(String sql,Connection connection,String ...param) throws Exception {
        //设置禁止自动提交
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement =  null;
        int  result = -1;
        try {
            //获取预处理对象
            preparedStatement =  connection.prepareStatement(sql);
            //设置占位参数
            if (param != null && param.length != 0){
                for (int i = 0; i < param.length;i++){
                    preparedStatement.setObject(i+1,param[i]);
                }
            }
            //获取结果集
           result = preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //设置允许自动提交
            connection.setAutoCommit(true);
            //返回处理结果
            return  result;
        }



    }

}
