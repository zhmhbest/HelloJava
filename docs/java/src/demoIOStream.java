import java.io.*;

public class demoIOStream {
    public static void main(String[] args) throws IOException {
        // 标准输出流
        PrintStream writer = System.out;

        // 输入
        ByteArrayOutputStream os = new ByteArrayOutputStream(128);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        osw.write("Hello!\n");
        osw.write("This is Stream.\n");
        osw.write("Bye!.\n");
        osw.close();
        byte[] data = os.toByteArray();
        writer.println(new String(data));

        // 输出
        char[] buffer = new char[32];
        InputStream is = new ByteArrayInputStream(data);
        InputStreamReader isr = new InputStreamReader(is);
        int ret = isr.read(buffer);
        isr.close();

        writer.printf("read size = %d\n\n", ret);
        writer.println(new String(buffer));
    }
}