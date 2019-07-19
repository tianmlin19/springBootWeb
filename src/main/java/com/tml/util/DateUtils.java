package com.tml.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期工具类
 */
public class DateUtils {


    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 一秒钟的毫秒数
     */
    public static final long MILLIS_PER_SECOND = 1000;

    /**
     * 一分钟的毫秒数
     */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

    /**
     * 一小时的毫秒数
     */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    /**
     * 一天的毫秒数
     */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    final static TimeZone loc = TimeZone.getTimeZone("GMT+8");
    final static TimeZone GMT = TimeZone.getTimeZone("GMT+0");

    public final static String toDatePattern = "yyyy-MM-dd HH:mm:ss";
    public final static String defaultDatePattern = "yyyyMMddHHmmss";
    public final static String datePattern = "yyyy-MM-dd";

    public final static String date2Pattern = "yyyy.MM.dd";
    public final static String datePattern2 = "yyyy/MM/dd";

    public static Long Calendar2Long(Calendar c) {
        if (c == null) {
            return null;
        }
        TimeZone tt = c.getTimeZone();
        long off = 0;
        if (tt != null) {
            off = GMT.getRawOffset() - loc.getRawOffset();
        }

        return c.getTimeInMillis() + off;
    }


    public static Calendar Date2UtcCalendar(Date l) {
        if (l == null) {
            return null;
        }
        Calendar c = Calendar.getInstance(GMT);

        c.setTimeInMillis(l.getTime() + loc.getRawOffset());
        return c;
    }


    public static Calendar Long2UtcCalendar(Long l) {
        if (l == null) {
            return null;
        }
        Calendar c = Calendar.getInstance(GMT);

        c.setTimeInMillis(l + loc.getRawOffset());
        return c;
    }

    /**
     * 获取当时到11：59：59.999的时间截
     */
    public static long getLittleTimeOfToday() {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTimeInMillis() - d.getTime();
    }

    public static Long String2DateLong(String str) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            if (str == null) {
                return null;
            }

            return sdf.parse(str).getTime();

        } catch (Exception ex) {
            final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf2.parse(str).getTime();
            } catch (Exception e) {
                final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                try {
                    return sdf3.parse(str).getTime();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }

            //ex.printStackTrace();
        }

        return null;
    }

    public static Date Long2Date(Long l) {
        if (l == null) {
            return null;
        }
        return new Date(l);
    }


    public static int offsetDate(long l) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(l);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        long dd = c.getTimeInMillis();
//		System.out.println(new Date(dd));
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(System.currentTimeMillis());
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        long dd2 = c2.getTimeInMillis();
//		System.out.println(dd2);
        return (int) ((dd2 - dd) / (3600 * 24 * 1000));
    }

    public static boolean isWeekend(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

    public static boolean isBefore1530(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        System.out.println(hour);
        System.out.println(min);
        if (hour <= 15 && min < 30) {
            return true;
        }
        return false;
    }


    /**
     * 使用预设Format格式化Date成字符串 yyyyMMddhhmmss
     */
    public static String formatStringDate(Date date) {
        return format(date, defaultDatePattern);
    }


    /**
     * 使用预设Format格式化Date成字符串 yyyyMMdd
     */
    public static String formatStringDay(Date date) {
        return format(date, datePattern);
    }

    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }

        return (returnValue);
    }

    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(toDatePattern);
            returnValue = df.format(date);
        }

        return (returnValue);
    }


    public static Date formate2Date(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        if (null == date) {
            date = new Date();
        }
        String time = sdf.format(date);
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return date;
        }
        return date;
    }


    public static Date formateDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null == date) {
            date = new Date();
        }
        String time = sdf.format(date);
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return date;
        }
        return date;
    }


    /**
     * @category 获取指定天数后的时间
     */
    public static String addDateXXDay(Date date, Integer position) {
        if (null == date) {
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.DATE, position); //日期加position天
        date = c.getTime();
        String stringFormDate = format(date, toDatePattern);
        return stringFormDate;
    }


    /**
     * @category 当月第一天
     */
    public static String getFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        return str.toString();

    }

    /**
     * @category 当月最后一天
     */
    public static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String day_last = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_last).append(" 23:59:59");
        return str.toString();
    }


    public static Long currentMilliscond() {
        Date date = new Date();

        return date.getTime();
    }


    public static String getToday(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static long timeStamp2Long(Timestamp t) {
        if (t != null) {
            return t.getTime();
        } else {
            return 0L;
        }
    }

    public static String formatDate(Timestamp t, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(t.getTime()));
    }

    public static String formatDate(long t, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(t));
    }

    /**
     * 返回dateFormat格式的时间字符串
     *
     * @param dateFormat e.g:yyyy-MM-dd HH:mm:ss
     */
    public static String getDateFormat(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    /**
     * 根据dateFormat格式构建时间
     *
     * @param dateFormat e.g:yyyy-MM-dd HH:mm:ss
     */
    public static Date getDate(String dateStrig, String dateFormat) {
        DateFormat df;
        try {
            df = new SimpleDateFormat(dateFormat);
            return df.parse(dateStrig);
        } catch (ParseException e) {
            try {
                df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                return df.parse(dateStrig);
            } catch (Exception ee) {
                return null;
            }
        }
    }

    public static Date getDate(String dateStrig) {
        DateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        try {
            return df.parse(dateStrig);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date nextDate(Date date, int num) {
        Calendar cla = Calendar.getInstance();
        cla.setTime(date);
        cla.add(Calendar.DAY_OF_YEAR, num);
        return cla.getTime();
    }


    public static int getCurrentYear() {
        Calendar cla = Calendar.getInstance();
        return cla.get(Calendar.YEAR);
    }

    /**
     * 取得两个日期之间的天数(如果edate>sdate返回正数，否则返回负数,请注意处理相差在0天的情况，不管哪个日期在前都会返回0)
     */
    public static int getDayDiff(Date sdate, Date edate) {
        if (sdate == null || edate == null) {
            return 0;
        }
        return (int) ((edate.getTime() - sdate.getTime()) / MILLIS_PER_DAY);
    }

    /**
     * 取得两个日期之间的月数
     */
    public static int getMonthDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        if (date1.after(date2)) {
            int year = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
            int n = year * 12 + (c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH));
            return n;
        } else {
            int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
            int n = year * 12 + (c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
            return n;
        }
    }

    /**
     * 取得一个月中最后一天的最后一秒calendar 23:59:59
     *
     * @param d 如果d=null则取得系统当前的月的
     * @author
     */
    public static Calendar getLastSecondInMonth(Date d) {
        Calendar c = Calendar.getInstance();
        if (d != null) {
            c.setTime(d);
        }

        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c;
    }

    /**
     * 返回day 天后0点0分0秒的时间
     */
    public static Date getDay0Clock(int day) {
        return getDay0Clock(new Date(), day);
    }

    /**
     * 返回day 天后0点0分0秒的时间
     */
    public static Date getDay0Clock(Date d, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
        c2.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + day);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        return c2.getTime();
    }

    /**
     * 返回day天后 的23点59分59秒的时间
     */
    public static Date getDay24Clock(int day) {
        return getDay24Clock(new Date(), day);
    }

    /**
     * 返回day天后 的23点59分59秒的时间
     */
    public static Date getDay24Clock(Date d, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
        c2.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + day);
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);
        c2.set(Calendar.MILLISECOND, 0);
        return c2.getTime();
    }

    /**
     * 返回month个月后 的23点59分59秒的时间
     */
    public static Date getMonth24Clock(int month) {
        Calendar c = Calendar.getInstance();
        Date d = new Date();
        c.setTime(d);

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c2.set(Calendar.MONTH, c.get(Calendar.MONTH) + month);
        c2.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);
        c2.set(Calendar.MILLISECOND, 0);
        return c2.getTime();
    }

    /**
     * 返回month个月后 的0点0分0秒的时间
     */
    public static Date getMonth0Clock(int month) {
        Calendar c = Calendar.getInstance();
        Date d = new Date();
        c.setTime(d);

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c2.set(Calendar.MONTH, c.get(Calendar.MONTH) + month);
        c2.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        return c2.getTime();
    }

    public static Date getMonth0Clock(int year, int month) {
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, year);
        c2.set(Calendar.MONTH, month - 1);
        c2.set(Calendar.DAY_OF_MONTH, 1);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        return c2.getTime();
    }


    /**
     * @category 获取2个日期相隔的天数
     */
    public static long getIntervalDay(String startdate, String enddate) {
        long m_intervalday = 0;//初始化时间间隔的值为0
        //使用的时间格式为yyyy-MM-dd
        SimpleDateFormat m_simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //创建两个Date对象
            Date date1 = m_simpledateformat.parse(startdate);
            Date date2 = m_simpledateformat.parse(enddate);
            m_intervalday = date2.getTime() - date1.getTime();//计算所得为微秒数
            m_intervalday = m_intervalday / 1000 / 60 / 60 / 24;//计算所得的天数
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return m_intervalday;
    }

    /**
     * @category 获取2个日期相隔的天数
     */
    public static int getIntervalDay(Date startdate, Date enddate) {
        Calendar s1 = Calendar.getInstance();
        Calendar s2 = Calendar.getInstance();
        s1.setTime(startdate);
        s2.setTime(enddate);
        s1.set(Calendar.HOUR_OF_DAY, 0);
        s1.set(Calendar.MINUTE, 0);
        s1.set(Calendar.SECOND, 0);
        s1.set(Calendar.MILLISECOND, 0);
        s2.set(Calendar.HOUR_OF_DAY, 0);
        s2.set(Calendar.MINUTE, 0);
        s2.set(Calendar.SECOND, 0);
        s2.set(Calendar.MILLISECOND, 0);
        int days = (int) ((s2.getTimeInMillis() - s1.getTimeInMillis()) / (3600 * 24 * 1000));
        return days;
    }


    public static String getDateTips(Date date) {
        String s = getDateFormat(date, DEFAULT_FORMAT);
        if (date != null) {
            int days = getIntervalDay(date, new Date());
            if (days == 0) {
                s = "今天 " + getDateFormat(date, "HH:mm:ss");
            } else if (days == 1) {
                s = "昨天 " + getDateFormat(date, "HH:mm:ss");
            } else if (days == 2) {
                s = "前天 " + getDateFormat(date, "HH:mm:ss");
            } else if (days == -1) {
                s = "明天 " + getDateFormat(date, "HH:mm:ss");
            } else if (days == -2) {
                s = "后天 " + getDateFormat(date, "HH:mm:ss");
            }
        }
        return s;
    }

    /**
     * 当前时间延后amount后时间
     */
    public static Date addYear(int amount) {
        return addYear(null, amount);
    }


    /**
     * 给定时间date延后amount后时间
     */
    public static Date addYear(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    /**
     * 当前时间延后amount后时间
     */
    public static Date addMonth(int amount) {
        return addMonth(null, amount);
    }


    /**
     * 给定时间date延后amount后时间
     */
    public static Date addMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }


    public static Date addWeek(Date date, int num) {
        Calendar cla = Calendar.getInstance();
        cla.setTime(date);
        cla.add(Calendar.DAY_OF_YEAR, num * 7);
        return cla.getTime();
    }


    /**
     * @param date 获取日期中的日
     */
    public static int getDay(Date date) {
        Calendar cla = Calendar.getInstance();
        cla.setTime(date);
        return cla.get(Calendar.DATE);
    }

    /**
     * 获取日期中月份
     */
    public static int getMonth(Date date) {
        Calendar cla = Calendar.getInstance();
        cla.setTime(date);
        return cla.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期中年份
     */
    public static int getYear(Date date) {
        Calendar cla = Calendar.getInstance();
        cla.setTime(date);
        return cla.get(Calendar.YEAR);
    }

    /**
     * 判断日期是否相同,只判断 年、月 是否相同
     */

    public static boolean isSameYearMonth(Date source, Date target) {

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(source);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(target);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

        boolean isSameMonth = cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);

        return isSameYear && isSameMonth;
    }


    /**
     * 判断日期是否相同,判断 年、月、日 是否相同
     */
    public static boolean isSameDate(Date source, Date target) {

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(source);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(target);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
        return isSameYear && isSameMonth && isSameDate;
    }

    /**
     * {方法功能中文描述}
     *
     * @author
     */

    public static void main(String[] args) {
        // Calendar c = getLastSecondInMonth(null);

		/*System.out.println(isWeekend(new Date()));
        System.out.println(formatDate(getMonth0Clock(2014,7).getTime(), "yyyy-MM-dd HH:mm:ss"));
		Random rd =new Random();
		int score = rd.nextInt(5)+1;
		System.out.println(score);
		
		
		isBefore1530(new Date());
		
		System.out.println(getDateTips(getDate("2015-09-07 12:12:12")));
		System.out.println(getDateTips(getDate("2015-09-08 12:12:12")));
		System.out.println(getDateTips(getDate("2015-09-09 12:12:12")));
		System.out.println(getDateTips(getDate("2015-09-10 12:12:12")));
		System.out.println(getDateTips(getDate("2015-09-11 12:12:12")));
		System.out.println(getDateTips(getDate("2015-09-12 12:12:12")));
		System.out.println(getDateTips(getDate("2015-09-13 12:12:12")));
		System.out.println(getDateTips(getDate("2015-09-14 12:12:12")));*/
//		System.out.println("当前时间="+format(new Date(), toDatePattern));
//		
//		System.out.println("今天24点="+format(getDay24Clock(0), toDatePattern));
//		System.out.println("今天00点="+format(getDay0Clock(0), toDatePattern));
//		
//		System.out.println("30天后24点="+format(getDay24Clock(30), toDatePattern));
//		System.out.println("30天后00点="+format(getDay0Clock(30), toDatePattern));
//		
//		
//		System.out.println("（0个月后）今天24点="+format(getMonth24Clock(0), toDatePattern));
//		System.out.println("（0个月后）今天00点="+format(getMonth0Clock(0), toDatePattern));
//		
//		System.out.println("1个月后24点="+format(getMonth24Clock(1), toDatePattern));
//		System.out.println("1个月后00点="+format(getMonth0Clock(1), toDatePattern));
//		
//		
//		System.out.println("1天后"+format(nextDate(new Date(),1),toDatePattern));
//		System.out.println("1个月后"+format(addMonth(1),toDatePattern));
//		System.out.println("1年后"+format(addYear(1),toDatePattern));
//		
//		System.out.println("2周后"+format(addWeek(new Date(),2),toDatePattern));

//        System.out.println(getMonth(new Date()));
//        System.out.println(getYear(new Date()));

//        System.out.println(getDateFormat(new Date(), "yyyy-MM-dd 00:00:00"));

//        System.out.println(getDateFormat(new Date(), "yyyy-MM-dd 23:59:59"));

//        System.out.println(formateDate(getDate("2018-11-10 10:10:10")));

        System.out.println(isSameYearMonth(new Date(), getDate("2018-10-01 10:10:10")));


    }
}
