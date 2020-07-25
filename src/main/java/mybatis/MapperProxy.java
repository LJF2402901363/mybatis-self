package mybatis;

import utils.DefaultResultSetHandler;
import utils.MyBatisJDBCUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Classname:MapperProxy
 *
 * @description:Mapper的代理对象类
 * @author: 陌意随影
 * @Date: 2020-07-25 18:06
 * @Version: 1.0
 **/
public class MapperProxy implements InvocationHandler {

    private Map<String, Mapper> mapperMap;
    private Connection connection;
    public MapperProxy( Map<String, Mapper> mapperMap,Connection connection) {
        this.mapperMap = mapperMap;
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取方法名
        String methodName = method.getName();
        //获取方法所在的类的名称
        String className = method.getDeclaringClass().getName();
        //组合成一个key
        String key = className+"."+methodName;
        //获取mapperMap中的的对应mapper
        Mapper mapper = mapperMap.get(key);
        //判断是否有对应的mapper
        if (mapper == null){
            throw  new RuntimeException("传的参数有误！");
        }

        //执行查询语句返回结果
        List<Object> objectList = MyBatisJDBCUtil.query(mapper, connection, new DefaultResultSetHandler(),args);
        //获取返回类型
        Class<?> returnType = method.getReturnType();
        //获取集合的字节码用于判断返回类型是否是集合的子类
        Class<Collection> collectionClass = Collection.class;
        if (objectList!= null && objectList.size() != 0){
            if (collectionClass.isAssignableFrom(returnType)){
                //如果返回类型是集合则返回一个集合
                return objectList;
            }else{
                //返回类型是一个对象则直接返回一个对象
                return  objectList.get(0);
            }
        }else{
            return  null;
        }



    }
}
