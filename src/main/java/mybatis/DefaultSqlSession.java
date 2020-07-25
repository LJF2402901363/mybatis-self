package mybatis;

import utils.MyBatisJDBCUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Classname:DefaultSqlSession
 *
 * @description:默认的SqlSession实现类
 * @author: 陌意随影
 * @Date: 2020-07-25 18:00
 * @Version: 1.0
 **/
public class DefaultSqlSession implements SqlSession {
    private Configurantion configurantion;
    private Connection connection ;
    public DefaultSqlSession(Configurantion configurantion) {
        this.configurantion = configurantion;
        this.connection = MyBatisJDBCUtil.getConnection(configurantion);
    }

    @Override
    public <T> T getMapper(Class<T> daoClass) {
        Object proxyInstance = Proxy.newProxyInstance(daoClass.getClassLoader(), new Class[]{daoClass}, new MapperProxy(configurantion.getMapperMap(), connection));
        return (T) proxyInstance;
    }

    @Override
    public void close() {
    if (this.connection!=null){
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
}
