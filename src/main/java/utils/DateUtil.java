package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classname:DateUtil
 *
 * @description:日期工具类
 * @author: 陌意随影
 * @Date: 2020-07-25 23:59
 * @Version: 1.0
 **/
public class DateUtil {
    /**
     * @Description :将日期格式化成字符串
     * @Date 0:01 2020/7/26 0026
     * @Param * @param date ：要格式化的日期
     * @return java.lang.String
     **/
    public static String dateToStr(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        return format;
    }
    public static Date strToDate(String dateStr){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
