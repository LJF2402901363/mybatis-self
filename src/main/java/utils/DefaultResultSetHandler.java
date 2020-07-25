package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classname:DefaultResultSetHandler
 * @description:处理结果集
 * @author: 陌意随影
 * @Date: 2020-07-25 21:12
 * @Version: 1.0
 **/
public class DefaultResultSetHandler<T> implements ResultSetHandler {
    /**
     * @param resultTypePath ：需要封装到的实体类
     * @return java.util.List<T>
     * @Description :处理并封装结果集对象到对应的实体类中去
     * @Date 22:58 2020/7/25 0025
     * @Param * @param resultSet ：包含数据库中实体类表信息的结果集
     **/
    @Override
    public List<T> resultSet(ResultSet resultSet, String resultTypePath) throws Exception {
        //通过反射获取返回类型的字节码
        Class<T> obj = (Class<T>) Class.forName(resultTypePath);
        if (obj == null) {
            throw new IllegalAccessException("封装类型有误！");
        }
        //获取构造器
        Constructor<T> constructor = obj.getConstructor();
        //获取所有的方法
        Method[] objMethods = obj.getMethods();
        List<T> list = new ArrayList<>();
        while (resultSet.next()) {
            //实例化一个实体类
            T t = constructor.newInstance();
            //获取实体类的set方法
            for (Method method : objMethods) {
                //获取方法的名字
                String methodName = method.getName();
                //找到set方法
                if (methodName.startsWith("set")) {
                    //获取set方法对应的属性名称
                    String fieldName = methodName.substring(3);
                    //获取set方法的参数类型,set方法只有一个参数
                    Class<?> parameterType = method.getParameterTypes()[0];
                    //获取set方法参数类型
                    String parameterTypeName = parameterType.getName();
                    //根据属性名从result中的获取对应数据库中的值并填充到实体对象中国
                    if ("java.lang.String".equals(parameterTypeName)) {
                        method.invoke(t, resultSet.getString(fieldName));
                    } else if ("java.lang.Integer".equals(parameterTypeName)) {
                        method.invoke(t, resultSet.getInt(fieldName));
                    } else if ("java.lang.Double".equals(parameterTypeName)) {
                        method.invoke(t, resultSet.getDouble(fieldName));
                    } else if ("java.lang.Byte".equals(parameterTypeName)) {
                        method.invoke(t, resultSet.getByte(fieldName));
                    } else if ("java.lang.Float".equals(parameterTypeName)) {
                        method.invoke(t, resultSet.getFloat(fieldName));
                    } else if ("java.lang.Boolean".equals(parameterTypeName)) {
                        method.invoke(t, resultSet.getBoolean(fieldName));
                    } else if ("java.util.Date".equals(parameterTypeName)) {
                        //将日期转化为指定格式的字符串
                        String dateToStr = DateUtil.dateToStr(resultSet.getDate(fieldName));
                        //将指定的字符串转换为日期
                        Date date = DateUtil.strToDate(dateToStr);
                        method.invoke(t, date);
                    }
                }

            }
            list.add(t);
        }
        return list;
    }
}
