package mybatis;

import java.io.InputStream;

/**
 * Classname:Resources
 * @description:加载资源文件
 * @author: 陌意随影
 * @Date: 2020-07-25 15:08
 * @Version: 1.0
 **/
public class Resources {
    /**
     * @Description :根据传入的资源文件名获取对应的输入流
     * @Date 15:17 2020/7/25 0025
     * @Param * @param filePath ：资源文件名，比如 demo.txt
     * @return java.io.InputStream
     **/
    public static InputStream getResourceAsStream(String filePath) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
    }
}
