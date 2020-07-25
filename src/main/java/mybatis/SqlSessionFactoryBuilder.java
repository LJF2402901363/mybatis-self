package mybatis;

import utils.MybatisUtil;

import java.io.InputStream;

/**
 * Classname:SqlSessionFactoryBuilder
 * @description:SQLSessionFactory的构建者
 * @author: 陌意随影
 * @Date: 2020-07-25 15:10
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) {
        Configurantion configurantion = MybatisUtil.LoaderConfiguration(inputStream);
        return new DefaultSqlSessionFactory(configurantion);
    }
}
