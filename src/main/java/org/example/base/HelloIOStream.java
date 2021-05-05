package org.example.base;

import java.io.*;

public class HelloIOStream {
    public static void main(String[] args) throws IOException {
        // 标准输出流
        PrintStream writer = System.out;

        // 输出内容到缓冲
        ByteArrayOutputStream os = new ByteArrayOutputStream(128);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        osw.write("Hello!\n");
        osw.write("This is Stream.\n");
        osw.write("Bye!.\n");
        osw.close();

        // 打印缓冲的内容到控制台
        byte[] data = os.toByteArray();
        writer.println(new String(data));

        // 读取内容到缓冲
        char[] buffer = new char[32];
        InputStream is = new ByteArrayInputStream(data);
        InputStreamReader isr = new InputStreamReader(is);
        int ret = isr.read(buffer);
        isr.close();

        // 打印缓冲的内容到控制台
        // writer.printf("read size = %d\n\n", ret);
        writer.println(new String(buffer));
    }
}