package org.example.text;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class HelloPinyin {
    public static String getPinyinStringArray(String text) {
        final HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

        StringBuilder builder = new StringBuilder(32);
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            String[] ts = null;
            try {
                ts = PinyinHelper.toHanyuPinyinStringArray(ch, format);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                System.err.println(e.getMessage());
            }
            if (null == ts || 0 == ts.length) {
                builder.append(ch);
            } else {
                builder.append(ts[0]);
            }
            builder.append(' ');
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String r = getPinyinStringArray("123常回家看看");
        System.out.println(r);
    }
}
