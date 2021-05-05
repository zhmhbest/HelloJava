package org.example.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HelloReg {
    static final Pattern pattern_mail = Pattern.compile("^[0-9a-zA-Z]+@(?:[0-9a-zA-Z]+\\.)+[0-9a-zA-Z]+$");
    static final Pattern pattern_phone = Pattern.compile("^1\\d{10}$");

    public interface ReplaceCallback {
        String replace(String match);
    }

    public static String findReplace(String str, String pattern, ReplaceCallback callback) {
        Matcher matcher = Pattern.compile(pattern).matcher(str);
        matcher.reset();
        boolean result = matcher.find();
        if (result) {
            StringBuffer sb = new StringBuffer();
            String to;
            do {
                to = callback.replace(matcher.group());
                if (null!=to)
                    matcher.appendReplacement(sb, to);
                else
                    matcher.appendReplacement(sb, "");
                result = matcher.find();
            } while (result);
            matcher.appendTail(sb);
            return sb.toString();
        }
        return str;
    }

    public static String preventHtml(String toFilter) {
        return findReplace(toFilter, "[<>& ]", match -> {
            switch (match) {
                case "<": return "&lt;";
                case ">": return "&gt;";
                case "&": return "&amp;";
                case " ": return "&nbsp;";
                default: return match;
            }
        });
    }

    public static void main(String[] args) {
        System.out.println(
                preventHtml("<html>[ & ]</html>")
        );
        System.out.println(
                pattern_mail.matcher("test@qq.com").matches()
        );
        System.out.println(
                pattern_phone.matcher("12355556666").matches()
        );
    }
}
