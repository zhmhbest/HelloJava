import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LongString {
    static class TypedString {
        public String value;
        public int type;
        public TypedString(String value, int type) {
            this.value = value;
            this.type = type;
        }
        @Override
        public String toString() {
            return "TypedString{" + value + '}';
        }
    }
    public static ArrayList<TypedString> parseFormatter(String text) {
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
                        arr.add(new TypedString(builder.toString(), 1));
                        builder.setLength(0);
                    }
                    arr.add(new TypedString(group.substring(2, group.length() - 1), 2));
                }
                lastPosition = matcher.end();
                result = matcher.find();
            }
            // final
            builder.append(text, lastPosition, text.length());
            if (builder.length() > 0) {
                arr.add(new TypedString(builder.toString(), 1));
            }
        }
        return arr;
    }
    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    public static String readText(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        byte[] buff = new byte[fis.available()];
        int size = fis.read(buff);
        return new String(buff, 0, size, StandardCharsets.UTF_8);
    }
    protected static final Map<String, ArrayList<TypedString>> fileFormatterHolder = new HashMap<>();
    public static ArrayList<TypedString> getTypedString(String fileName) {
        if (fileFormatterHolder.containsKey(fileName)) {
            return fileFormatterHolder.get(fileName);
        } else {
            try {
                String text = readText(fileName);
                ArrayList<TypedString> formatter = parseFormatter(text);
                fileFormatterHolder.put(fileName, formatter);
                return formatter;
            } catch (IOException e) {
                return null;
            }
        }
    }
    public static LongString make(String fileName) {
        ArrayList<TypedString> formatter = getTypedString(fileName);
        if (null == formatter) return null;
        return new LongString(formatter);
    }
    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    protected ArrayList<TypedString> formatter;
    protected final Map<String, String> varMap = new HashMap<>();
    public LongString(ArrayList<TypedString> formatter) {
        this.formatter = formatter;
    }
    public void setVariable(String name, String value) {
        if (null == value) {
            varMap.remove(name);
        } else {
            varMap.put(name, value);
        }
    }
    public void clearVariables() {
        varMap.clear();
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (TypedString item : this.formatter) {
            if (1 == item.type) {
                builder.append(item.value);
            } else if (2 == item.type) {
                if (varMap.containsKey(item.value)) {
                    builder.append(varMap.get(item.value));
                } else {
                    builder.append("${Undefined variable '").append(item.value).append("'}");
                }
            }
        }
        return builder.toString();
    }
}

public class demoLongString {
    public static final String CURRENT_DIRECTORY = demoLongString.class.getResource("/").getPath();
    public static void main(String[] args) throws IOException {
        LongString ls = LongString.make(CURRENT_DIRECTORY + "demoLongString.jsp");
        assert ls != null;
        ls.setVariable("base", "我是变量");
        System.out.println(ls);
        ls.clearVariables();
        System.out.println(ls);
    }
}