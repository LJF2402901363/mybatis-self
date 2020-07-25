package mybatis;

/**
 * Classname:Mapper
 *
 * @description:封装sql语句和返回值类型全限定类名
 * @author: 陌意随影
 * @Date: 2020-07-25 17:04
 * @Version: 1.0
 **/
public class Mapper {
    //返回值了类型的类限定名
    private String resultTypePath;
    //sql语句
    private String sql;
    //sql操作类型
    private  int sqlType;
    /**查询类型*/
    public  static int SELECT = 1;
    /**插入类型*/
    public  static int INSERT = 2;
    /**删除类型*/
    public  static int DELETE = 3;
    /**更新类型*/
    public  static int UPDATE = 4;
    public String getResultTypePath() {
        return resultTypePath;
    }
    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }
    public void setResultTypePath(String resultTypePath) {
        this.resultTypePath = resultTypePath;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "Mapper{" +
                "resultTypePath='" + resultTypePath + '\'' +
                ", sql='" + sql + '\'' +
                ", sqlType=" + sqlType +
                '}';
    }
}
