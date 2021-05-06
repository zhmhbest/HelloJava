package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class IO {
    public static InputStream getResourceStream(String name) {
        return IO.class.getResourceAsStream(name);
    }

    /**
     * 读取资源目录文本文件
     *
     * @param name 资源位置
     * @return 文本内容
     */
    public static String readResourceText(String name) {
        InputStream is = getResourceStream(name);
        if (null != is) {
            try {
                int fileSize = is.available();
                byte[] buffer = new byte[fileSize];
                int readSize = is.read(buffer);
                if (readSize != fileSize) { return null; }
                return new String(buffer, 0, fileSize, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String readText(String fileName) {
        try {
            return readText(new FileInputStream(fileName));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static String readText(InputStream is) {
        try {
            byte[] buff = new byte[is.available()];
            int size = is.read(buff);
            return new String(buff, 0, size, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(readResourceText("/demo.json"));
        System.out.println(readText("src/main/resources/demo.json"));
    }
}
