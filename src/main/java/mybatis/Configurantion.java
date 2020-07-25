package mybatis;
import java.util.HashMap;
import java.util.Map;

/**
 * Classname:Configurantion
 *
 * @description:数据库连接配置信息类
 * @author: 陌意随影
 * @Date: 2020-07-25 15:40
 * @Version: 1.0
 **/
public class Configurantion {
    //连接驱动
    private  String driver;
    //连接用户名
    private  String username;
    //密码
    private  String password;
    //连接URL
    private String url;
    //dao接口的关键配置信息map
    private Map<String,Mapper> mapperMap = new HashMap<>();

    public Map<String, Mapper> getMapperMap() {
        return mapperMap;
    }

    public void setMapperMap(Map<String, Mapper> mapperMap) {
        this.mapperMap.putAll(mapperMap);
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Configurantion{" +
                "driver='" + driver + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
