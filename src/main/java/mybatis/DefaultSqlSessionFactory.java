package mybatis;

/**
 * Classname:DefaultSqlSessionFactory
 *
 * @description:构建SQLSession的默认工厂
 * @author: 陌意随影
 * @Date: 2020-07-25 17:52
 * @Version: 1.0
 **/
public class DefaultSqlSessionFactory  implements SqlSessionFactory {
     private Configurantion configurantion ;
     public DefaultSqlSessionFactory(Configurantion configurantion){
         this.configurantion = configurantion;
     }
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configurantion);
    }
}
