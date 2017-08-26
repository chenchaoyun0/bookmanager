package com.sttx.bookmanager.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.sttx.bookmanager.util.exception.UserException;

/**
 * 
 * @Description
 * @author chenchaoyun[chenchaoyun@sttxtech.com]
 * @date 2017年8月26日 下午3:47:17
 */
public class BookDateUtils {
    public static final String defaultPrsePatterns = "yyyy-MM-dd";
    public static final String timePrsePatterns = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * @Description 字符串转日期
     * @param str
     *            日期字符串
     * @param parsePatterns
     *            转化格式
     * @return
     * @throws UserException
     *             ccbc异常
     */
    public static Date parseDate(String str, String... parsePatterns) throws UserException {
        try {
            if(parsePatterns==null || parsePatterns.length==0){
                return DateUtils.parseDate(str, defaultPrsePatterns);
            }else{
                return DateUtils.parseDate(str, parsePatterns);
            }
        } catch (ParseException e) {
            throw new UserException(e);
        }
    }
    
    /**
     * @Description 获取日期实例
     * @param date
     *            字符串日期
     * @return
     * @throws UserException
     */
    public static Calendar getCalendarInstance(String date) throws UserException {
        Calendar cal1=Calendar.getInstance();
        cal1.setTime(parseDate(date, defaultPrsePatterns));
        return cal1;
    }
       
    
    /**
     * @Description 计算date1的月份数
     * @param date1
     *            日期
     * @param defaultPrsePatterns
     *            日期格式
     * @return
     * @throws UserException
     */
    public static int getMonthNum(String date1, String... defaultPrsePatterns) throws UserException {
        Calendar cal1=Calendar.getInstance();
        if(defaultPrsePatterns==null || defaultPrsePatterns.length==0){
            cal1.setTime(parseDate(date1,"yyyy-MM-dd"));
        }else{
            cal1.setTime(parseDate(date1,defaultPrsePatterns));
        }
        return (cal1.get(1)*12+cal1.get(2));
    }   
    /**
     * @Description 计算date1的月份数
     * @param date1 日期
     * @return
     */
    public static int getMonthNum(Date date1)  {
        Calendar cal1=Calendar.getInstance();
        cal1.setTime(date1);
        return (cal1.get(1)*12+cal1.get(2));
    }

    
    /**
     * @Description 日期格式化
     * @param date 日期
     * @param defaultPrsePatterns 格式化模板
     * @return
     */
    public static String formateDate(Date date,String defaultPrsePatterns){
      return   DateFormatUtils.format(date, defaultPrsePatterns);
    }
    /**
     * @Description 日期格式化
     * @param date 日期
     * @param defaultPrsePatterns 格式化模板
     * @return
     */
    public static String formateDate(Calendar date,String defaultPrsePatterns){
      return   DateFormatUtils.format(date, defaultPrsePatterns);
    }
        
    /**
     * @Description 日期格式化
     * @param date
     *            字符串日期
     * @param datePatterns
     *            字符串日期 格式
     * @param destPrsePatterns
     *            格式化模板
     * @return
     * @throws UserException
     */
    public static String formateDate(String date, String datePatterns, String destPrsePatterns) throws UserException {
        return   DateFormatUtils.format(parseDate(date, datePatterns),destPrsePatterns);
      }
    
    /**
     * @Description 设置日期的天
     * @param date
     *            日期
     * @param d
     *            天
     * @return
     * @throws UserException
     */
    public static String setDay(String date, int d) throws UserException {
        Calendar cl = BookDateUtils.getCalendarInstance(date);
        cl.set(5, d);
        return DateFormatUtils.format(cl, BookDateUtils.defaultPrsePatterns);

    }
    
    /**
     * @Description 设置日期的天
     * @param date
     *            日期
     * @param d
     *            天
     * @return
     * @throws UserException
     */
    public static int getDay(String date) throws UserException {
        Calendar cl = BookDateUtils.getCalendarInstance(date);
        
        return cl.get(5);

    }
    
    /**
     * @Description 增加日期的天
     * @param date
     *            日期
     * @param d
     *            天
     * @return
     * @throws UserException
     */
    public static String addDay(String date, int d) throws UserException {
        Calendar cl = BookDateUtils.getCalendarInstance(date);
        cl.add(5, d);
        return DateFormatUtils.format(cl, BookDateUtils.defaultPrsePatterns);

    }
   
    /**
     * @Description 为日期月份加m月数
     * @param date
     *            日期
     * @param m
     *            月数
     * @return
     * @throws UserException
     */
    public static String addMonth(String date, int m) throws UserException {
        Calendar cl = BookDateUtils.getCalendarInstance(date);
        cl.add(2, m);
        return DateFormatUtils.format(cl, BookDateUtils.defaultPrsePatterns);

    }
    
    /**
     * @Description
     * @param beginDate
     * @param endDate
     * @return
     * @throws UserException
     */
    public static long sub(String endDate, String beginDate) throws UserException {
       return  (BookDateUtils.parseDate(endDate).getTime() - BookDateUtils.parseDate(beginDate).getTime())/(1000*24*60*60);
    }
   
    

}
