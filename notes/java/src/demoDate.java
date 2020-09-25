/*
 * ArrayList: 是一个长度不固定的数组
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class demoDate {
    public static void main(String[] args) {
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
