package utils;

import mybatis.Configurantion;
import mybatis.Mapper;
import mybatis.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classname:MybatisUtil
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2020-07-25 15:44
 * @Version: 1.0
 **/
public class MybatisUtil {
    /**
     * @Description :通过资源文件流加载配置信息
     * @Date 15:45 2020/7/25 0025
     * @Param * @param inputStream ：
     * @return mybatis.Configurantion
     **/
    public static Map<String, Mapper> XMLLoader(String mapperPath) throws DocumentException {
        InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);
        //获取读取流对象
        SAXReader reader = new SAXReader();
        //获取document对象
        Document document = reader.read(resourceAsStream);
        //获取根节点，根节点就是mapper节点
        Element rootElement = document.getRootElement();
        //获取mapper的namespace的属性值
        String namespace = rootElement.attributeValue("namespace");
        //获取mapper下的每个select节点
        List nodes = rootElement.selectNodes("//select");
        Map<String, Mapper> mapperMap = new HashMap<>();
        if (nodes!=null && nodes.size() != 0){
            Map<String, Mapper> mappers = getMapperMap(namespace, nodes,Mapper.SELECT);
            mapperMap.putAll(mappers);
        }
        //获取mapper下的每个insert节点
         nodes = rootElement.selectNodes("//insert");
        if (nodes!=null && nodes.size() != 0){
            Map<String, Mapper> mappers = getMapperMap(namespace, nodes,Mapper.INSERT);
            mapperMap.putAll(mappers);
        }
        //获取mapper下的每个delete节点
         nodes = rootElement.selectNodes("//delete");
        if (nodes!=null && nodes.size() != 0){
            Map<String, Mapper> mappers = getMapperMap(namespace, nodes,Mapper.DELETE);
            mapperMap.putAll(mappers);
        }
        //获取mapper下的每个update节点
        nodes = rootElement.selectNodes("//update");
        if (nodes!=null && nodes.size() != 0){
            Map<String, Mapper> mappers = getMapperMap(namespace, nodes,Mapper.UPDATE);
            mapperMap.putAll(mappers);
        }

        return mapperMap;
    }
    /**
     * @Description :根据namespace，操作类型节点以及操作类型获取封装的Map
     * @Date 20:19 2020/7/25 0025
     * @Param * @param namespace： dao接口的全限定类名
     * @param nodes ：操作类型的节点集合
     * @param sqlType ：操作类型
     * @return java.util.Map<java.lang.String,mybatis.Mapper>
     **/
    private static Map<String, Mapper> getMapperMap(String namespace, List nodes,int sqlType) {
        Map<String,Mapper> mapperMap = new HashMap<>();
        for (Object o:nodes){
            Element element = (Element) o;
            //获取select的id属性值，相当于dao接口的方法名
            String daoMethodName = element.attributeValue("id");
            //获取select的resultType的属性值，相当于dao接口的返回值类型
            String resultType = element.attributeValue("resultType");
            //组建一个dao全限定类名和方法名组成的key，比如  dao.UserDao.getAll,标识dao包下的USerDao接口中的getAll方法
            String key = namespace+"."+daoMethodName;
            //获取select标签的sql语句
            String sql = element.getText();
            Mapper mapper = new Mapper();
            //设置返回值类型
            mapper.setResultTypePath(resultType);
            //设置sql语句
            mapper.setSql(sql);
            //将mapper存入Map中
            mapperMap.put(key,mapper);
            //设置sql操作类型
            mapper.setSqlType(sqlType);
        }
        return mapperMap;
    }

    /**
   * @Description :加载XML的配置信息
   * @Date 15:45 2020/7/25 0025
   * @Param * @param inputStream ：资源文件流
   * @return mybatis.Configurantion
   **/
    public static Configurantion LoaderConfiguration(InputStream inputStream) {
        //获取读取流对象
        SAXReader reader = new SAXReader();
        Configurantion configurantion = new Configurantion();
        try {
            //根据资源文件流获取document对象
            Document document = reader.read(inputStream);
            //获取文档的根节点
            Element rootElm = document.getRootElement();
            //获取默认的数据库配置
            List list = rootElm.selectNodes("environments");
            if (list == null || list.size() ==0){
                throw  new RuntimeException("尚未配置数据库连接环境信息！");
            }
            //获取environments的id
            Element environmentsEle = (Element) list.get(0);
            String defaultEnvironmentId = environmentsEle.attributeValue("default");
            //获取所有的已经配置的数据库环境
            List environmentNodes = environmentsEle.elements();
            if (environmentNodes == null || environmentNodes.size() ==0){
                throw  new RuntimeException("尚未配置数据库连接环境信息！");
            }
            //获取与默认的environments的id相同的environment节点
            List propertyList = null;
            for (Object o: environmentNodes){
                Element environmentEle = (Element) o;
                //找到默认的数据库连接环境
                if (defaultEnvironmentId.equals(environmentEle.attributeValue("id"))){
                   //获取数据库的链接信息，driver,username,password,url
                    //获取该环境下的property标签
                     propertyList = environmentEle.selectNodes("//property");
                     //跳出循环
                    break;
                }
            }
            if(propertyList  == null){
                throw  new RuntimeException("尚未配置数据库连接环境信息！");
            }
            for (Object p:propertyList  ) {
                Element propertyEle = (Element) p;
                //获取property标签的name属性值
                String name = propertyEle.attributeValue("name");
                if ("url".equals(name)){
                    //设置URL的值
                    configurantion.setUrl(propertyEle.attributeValue("value"));
                }
                if ("driver".equals(name)){
                    //设置driver的值
                    configurantion.setDriver(propertyEle.attributeValue("value"));
                }
                if ("username".equals(name)){
                    //设置username的值
                    configurantion.setUsername(propertyEle.attributeValue("value"));
                }
                if ("password".equals(name)){
                    //设置password的值
                    configurantion.setPassword(propertyEle.attributeValue("value"));
                }
            }
            //定位到mybatis配置文件中的 mappers 中的mapper标签的所有节点
            List mapperNodes = rootElm.selectNodes("mappers/mapper");
            if (mapperNodes == null || mapperNodes.size() == 0){
                throw  new RuntimeException("您未配置mapper的信息！");
            }
            //逐个遍历mapper节点然后获取其中resource的值
            for (Object e:mapperNodes){
                Element element = (Element) e;
                String  daoPath = element.attributeValue("resource");
                if (daoPath != null){
                    //是XML配置
                    Map<String, Mapper> mapperMap = XMLLoader(daoPath);
                    configurantion.setMapperMap(mapperMap);
                }else{
                    //是注解配置
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return  configurantion;
    }
}
