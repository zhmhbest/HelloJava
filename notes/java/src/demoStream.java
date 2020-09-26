import java.io.PrintStream;         // 字符流

import java.io.InputStream;         // 字节流
import java.io.InputStreamReader;   // 字符流

import java.io.OutputStream;        // 字节流
import java.io.OutputStreamWriter;  // 字符流

public class demoStream {
    public static void main(String[] args) {
        PrintStream writer = System.out;
        writer.print("Hello");
    }
}
