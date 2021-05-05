package org.example.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;


public class HelloDateTime {
    /**
     * 格式化Calendar对象
     * @param calendar 时间
     * @return String
     */
    public static String formatCalendar(Calendar calendar) {
        return String.format(
                "%d-%d-%d %d:%d:%d ms=%d dow=%d doy=%d wom=%d woy=%d",
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND),
                calendar.get(Calendar.MILLISECOND),
                calendar.get(Calendar.DAY_OF_WEEK),
                calendar.get(Calendar.DAY_OF_YEAR),
                calendar.get(Calendar.WEEK_OF_MONTH),
                calendar.get(Calendar.WEEK_OF_YEAR)
        );
    }

    public static void main(String[] args) {
        /*
         * Calendar -> Long(TimeMillis)
         */
        Calendar c1 = Calendar.getInstance();
        System.out.println(c1.getTimeInMillis());

        /*
         * Calendar -> String
         */
        Calendar c2 = Calendar.getInstance();
        System.out.println(formatCalendar(c2));

        /*
         * Calendar: 日期计算
         */
        Calendar c3 = Calendar.getInstance();
        c3.add(Calendar.YEAR, 2);
        System.out.println(formatCalendar(c3));

        /*
         * Long(TimeMillis): 日期计算
         */
        final int TIME_MILLIS_SECOND = 1000;
        final int TIME_MILLIS_MINUTE = 60 * TIME_MILLIS_SECOND;
        final int TIME_MILLIS_HOUR = 60 * TIME_MILLIS_MINUTE;
        final int TIME_MILLIS_DAY = 24 * TIME_MILLIS_HOUR;
        final SimpleDateFormat DayIDFormat = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat MinuteIDFormat = new SimpleDateFormat("yyyyMMddHHmm");
        //
        long currentTimeMillis = System.currentTimeMillis();
        // day
        String dayNow = DayIDFormat.format(new Date(currentTimeMillis));
        String dayBefore1 = DayIDFormat.format(new Date(currentTimeMillis - TIME_MILLIS_DAY));
        String dayBefore2 = DayIDFormat.format(new Date(currentTimeMillis - TIME_MILLIS_DAY * 2));
        String dayBefore3 = DayIDFormat.format(new Date(currentTimeMillis - TIME_MILLIS_DAY * 3));
        System.out.println(dayNow);
        System.out.println(dayBefore1);
        System.out.println(dayBefore2);
        System.out.println(dayBefore3);
        //minute
        String minuteBefore4 = MinuteIDFormat.format(new Date(currentTimeMillis - TIME_MILLIS_MINUTE * 4));
        String minuteBefore8 = MinuteIDFormat.format(new Date(currentTimeMillis - TIME_MILLIS_MINUTE * 8));
        System.out.println(minuteBefore4);
        System.out.println(minuteBefore8);

        /*
         * Date -> String
         */
        Date date = new Date();
        DateFormat df = DateFormat.getDateTimeInstance();
        System.out.println(df.format(date));
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        System.out.println(ft.format(date));

        /*
         * String -> Date
         */
        SimpleDateFormat sdt = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date parseResult = null;
        try {
            parseResult = sdt.parse("1234-12-24 11:22:33");
        } catch (ParseException e) {
            System.err.println(e.toString());
        }
        System.out.println(parseResult);
    }
}
