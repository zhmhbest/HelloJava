import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;

public class demoDateTime {
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
         * Calendar: 当前时间
         */
        Calendar c = Calendar.getInstance();
        System.out.println(formatCalendar(c));
        System.out.println(c.getTimeInMillis());

        /*
         * Calendar: 日期计算
         */
        c.add(Calendar.YEAR, 2);
        System.out.println(formatCalendar(c));

        /*
         * TimeMillis: 日期计算
         */
        final int TIME_MILLIS_SECOND = 1000;
        final int TIME_MILLIS_MINUTE = 60 * TIME_MILLIS_SECOND;
        final int TIME_MILLIS_HOUR = 60 * TIME_MILLIS_MINUTE;
        final int TIME_MILLIS_DAY = 24 * TIME_MILLIS_HOUR;
        System.out.println(new Date(System.currentTimeMillis() - 2 * TIME_MILLIS_DAY));

        /*
         * Date: 日期 -> 字符串
         */
        Date date = new Date();
        DateFormat df = DateFormat.getDateTimeInstance();
        System.out.println(df.format(date));
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        System.out.println(ft.format(date));

        /*
         * Date: 字符串 -> 日期
         */
        Date date_parse = null;
        try {
            date_parse = ft.parse("1234-12-24 11:22:33");
        } catch (ParseException e) {
            System.err.println(e.toString());
        }
        System.out.println(date_parse);
    }
}
