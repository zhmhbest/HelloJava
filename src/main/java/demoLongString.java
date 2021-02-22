import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class TemplateString {
    // TypedString
    protected static final int TYPED_STR = 1;
    protected static final int TYPED_VAR = 2;
    protected static class TypedString {
        public String value;
        public int type;
        public TypedString(String value, int type) {
            this.value = value;
            this.type = type;
        }
    }
    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    // Split
    protected static ArrayList<TypedString> splitString(String text) {
        ArrayList<TypedString> arr = new ArrayList<>();
        final Pattern pattern = Pattern.compile("\\\\.|\\$\\{.+?\\}");
        int lastPosition = 0;
        {
            Matcher matcher = pattern.matcher(text);
            matcher.reset();
            StringBuilder builder = new StringBuilder(512);
            String group;
            boolean result = matcher.find();
            while (result) {
                builder.append(text, lastPosition, matcher.start());
                group = matcher.group();
                // \?
                if (group.startsWith("\\")) {
                    char ch = group.charAt(1);
                    switch (ch) {
                        case '\\':
                            builder.append('\\');
                            break;
                        case 'n':
                            builder.append('\n');
                            break;
                        case 'r':
                            builder.append('\r');
                            break;
                        case 't':
                            builder.append('\t');
                            break;
                        default:
                            builder.append(ch);
                            break;
                    }
                }
                // ${}
                else if (group.startsWith("$")) {
                    if (builder.length() > 0) {
                        arr.add(new TypedString(builder.toString(), TYPED_STR));
                        builder.setLength(0);
                    }
                    arr.add(new TypedString(group.substring(2, group.length() - 1).trim(), TYPED_VAR));
                }
                lastPosition = matcher.end();
                result = matcher.find();
            }
            // final
            builder.append(text, lastPosition, text.length());
            if (builder.length() > 0) {
                arr.add(new TypedString(builder.toString(), TYPED_STR));
            }
        }
        return arr;
    }
    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    // TemplateString
    protected ArrayList<TypedString> templateString;
    protected final Map<String, String> variableMap = new HashMap<>();
    public void setVariable(String name, String value) {
        if (null == value)
            variableMap.remove(name);
        else
            variableMap.put(name, value);
    }
    public void clearVariables() {
        variableMap.clear();
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (TypedString item : this.templateString) {
            if (TYPED_STR == item.type) {
                builder.append(item.value);
            } else if (TYPED_VAR == item.type) {
                if (variableMap.containsKey(item.value)) {
                    builder.append(variableMap.get(item.value));
                } else {
                    builder.append("${Undefined variable '").append(item.value).append("'}");
                }
            }
        }
        return builder.toString();
    }
    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    public static String readText(String fileName) throws IOException {
        return readText(new FileInputStream(fileName));
    }
    public static String readText(InputStream is) throws IOException {
        byte[] buff = new byte[is.available()];
        int size = is.read(buff);
        return new String(buff, 0, size, StandardCharsets.UTF_8);
    }
    public static TemplateString make(String s) {
        if (null == s) return null;
        ArrayList<TypedString> templateString = splitString(s);
        if (templateString.isEmpty()) return null;
        TemplateString t = new TemplateString();
        t.templateString = templateString;
        return t;
    }
    public static TemplateString make(InputStream is) {
        String s = null;
        try {
            s = readText(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return make(s);
    }
}


public class demoLongString {
    public static void main(String[] args) throws IOException {
        // System.out.println(
        //         TemplateString.readText(demoLongString.class.getResourceAsStream("demoLongString.jsp")) + "|"
        // );
        // System.out.println(
        //         TemplateString.readText(
        //                 TemplateString.class.getResource("/").getPath() + "demoLongString.jsp"
        //         ) + "|"
        // );
        TemplateString ls = TemplateString.make(TemplateString.class.getResourceAsStream("demoLongString.jsp"));
        assert ls != null;
        ls.setVariable("base", "我是变量");
        System.out.println(ls);
        System.out.println("--------");
        ls.clearVariables();
        System.out.println(ls);
    }
}