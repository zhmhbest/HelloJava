import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class demoDateTime {
    public static void main(String[] args) {
        /*
         * Calendar
         */
        Calendar c = Calendar.getInstance();
        System.out.printf(
                "%d-%d-%d %d:%d:%d\n",
                c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND)
        );

        /*
         * 日期 -> 字符串
         */
        Date date = new Date();
        DateFormat df = DateFormat.getDateTimeInstance();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        System.out.println(df.format(date));
        System.out.println(ft.format(date));

        /*
         * 字符串 -> 日期
         */
        Date date_parse = null;
        try {
            date_parse = ft.parse("2020-09-25 08:11:47");
        } catch (ParseException e) {
            System.err.println(e.toString());
        }
        System.out.println(date_parse);
    }
}
