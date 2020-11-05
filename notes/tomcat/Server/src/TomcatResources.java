import org.apache.catalina.startup.Bootstrap;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


class Coder {
    public static byte[] readFile(File f) throws IOException {
        FileInputStream in = new FileInputStream(f);
        int size = in.available();
        byte[] src = new byte[size];
        return size == in.read(src) ? src : null;
    }

    public static byte[] readFile(String filename) throws IOException {
        return readFile(new File(filename));
    }

    public static void writeFile(File f, byte[] data) throws IOException {
        FileOutputStream out = new FileOutputStream(f);
        out.write(data);
        out.close();
    }

    public static void writeFile(String filename, byte[] data) throws IOException {
        writeFile(new File(filename), data);
    }


    public static String encodeBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String encodeBase64(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] decodeBase64AsBytes(String base64Code) {
        return Base64.getDecoder().decode(base64Code);
    }

    public static String decodeBase64AsString(String base64Code) {
        return new String(Base64.getDecoder().decode(base64Code), StandardCharsets.UTF_8);
    }


    public static String encodeCompressedBase64(byte[] bytes) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        gzip = new GZIPOutputStream(out);
        gzip.write(bytes);
        gzip.close();
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    public static byte[] decodeCompressedBase64AsBytes(String base64Code) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream gzip;
        gzip = new GZIPInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(base64Code)));
        byte[] buffer = new byte[256];
        int size;
        while ((size = gzip.read(buffer)) >= 0) {
            out.write(buffer, 0, size);
        }
        return out.toByteArray();
    }


    public static String encodeFileCompressedBase64(String filename) throws IOException {
        return encodeCompressedBase64(readFile(filename));
    }

    public static void decodeFileCompressedBase64(String filename, String base64Code) {
        try {
            writeFile(filename, decodeCompressedBase64AsBytes(base64Code));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public class TomcatResources {
    private static final String currentDirectory =
            new File(TomcatRunner.class.getResource("/").getPath()).getAbsolutePath()
                    .replaceAll("\\\\", "/");
    private static final String tomcatBinaryDirectory =
            new File(Bootstrap.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent()
                    .replaceAll("\\\\", "/");
    private static final String tomcatDirectory =
            new File(tomcatBinaryDirectory).getParent()
                    .replaceAll("\\\\", "/");

    public static void defaultProperties() {
        System.setProperty("catalina.home", tomcatDirectory);
        System.setProperty("catalina.base", currentDirectory);
    }

    public static void loadProperties(String filename) {
        if (null == filename) return;
        // 自定义配置
        Properties properties = new Properties();
        InputStream in = TomcatRunner.class.getClassLoader().getResourceAsStream(String.format("%s.properties", filename));
        if (null == in) {
            System.out.printf("Can not find %s\n", filename);
            return;
        }
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        for (Enumeration<?> keys = properties.propertyNames(); keys.hasMoreElements(); ) {
            String key = keys.nextElement().toString();
            System.setProperty(key, properties.getProperty(key));
        }
    }

    public static void defaultConfigurations() {
        String baseDirectory = System.getProperty("catalina.base");
        if (!baseDirectory.equals(currentDirectory)) return;
        defaultDirectories(baseDirectory);

        final String name_catalina_properties = String.format("%s/conf/%s", baseDirectory, "catalina.properties");
        final String data_catalina_properties
                = "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";
        Coder.decodeFileCompressedBase64(name_catalina_properties, data_catalina_properties);

        final String name_catalina_policy = String.format("%s/conf/%s", baseDirectory, "catalina.policy");
        final String data_catalina_policy =
                "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";
        Coder.decodeFileCompressedBase64(name_catalina_policy, data_catalina_policy);

        final String name_context = String.format("%s/conf/%s", baseDirectory, "context.xml");
        final String data_context =
                "H4sIAAAAAAAAALOxr8jNUShLLSrOzM+zVTLUM1BSSM1Lzk/JzEu3VQoNcdO1ULK347Jxzs8rSa0oseNSAAKb8MSS5IzUlKDU4vzSouRUu3BXJ11PPzf98tQkPaB5NvroCrhs9GEmAACXp2j+cgAAAA==";
        Coder.decodeFileCompressedBase64(name_context, data_context);

        final String name_tomcat_users = String.format("%s/conf/%s", baseDirectory, "tomcat-users.xml");
        final String data_tomcat_users =
                "H4sIAAAAAAAAAJWQMQ7CMAxF957CeMaUbgxJu3ECOECaRG2lJkFJChyfhCKkVgjB8mXrfz9bZs3djHDVPgzOcqx2ewRtpVOD7TieT0c6YFMXLDojRaQppGRqN0QFAPNu1JDFCqM5ziEs6w9erqqXlSmQZTEGFxHCzXnFkZkpRGo1yV7YTqsan6SwWrHitC72/1C2X26arZ9gbwpR+ky5+NQDJXz+DF8BAAA=";
        Coder.decodeFileCompressedBase64(name_tomcat_users, data_tomcat_users);

        final String name_web = String.format("%s/conf/%s", baseDirectory, "web.xml");
        final String data_web =
                "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";
        Coder.decodeFileCompressedBase64(name_web, data_web);
    }

    private static void defaultDirectories(String baseDirectory) {
        File dir_logs = new File(baseDirectory + "/logs");
        File dir_temp = new File(baseDirectory + "/temp");
        File dir_work = new File(baseDirectory + "/work");
        File dir_conf = new File(baseDirectory + "/conf");
        System.out.printf("%s: new?=%b\n", dir_logs, dir_logs.mkdir());
        System.out.printf("%s: new?=%b\n", dir_temp, dir_temp.mkdir());
        System.out.printf("%s: new?=%b\n", dir_work, dir_work.mkdir());
        System.out.printf("%s: new?=%b\n", dir_conf, dir_conf.mkdir());
    }

}
