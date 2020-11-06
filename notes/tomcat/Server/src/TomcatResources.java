import org.apache.catalina.startup.Bootstrap;

import java.io.*;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class TomcatResources {
    private static final String currentDirectory =
            new File(TomcatResources.class.getResource("/").getPath()).getAbsolutePath()
                    .replaceAll("\\\\", "/");
    private static final String tomcatBinaryDirectory =
            new File(Bootstrap.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent()
                    .replaceAll("\\\\", "/");
    private static final String tomcatDirectory =
            new File(tomcatBinaryDirectory).getParent()
                    .replaceAll("\\\\", "/");


    public static byte[] readFile(File f) throws IOException {
        FileInputStream in = new FileInputStream(f);
        int size = in.available();
        byte[] src = new byte[size];
        return size == in.read(src) ? src : null;
    }

    public static void writeFile(File f, byte[] data) throws IOException {
        FileOutputStream out = new FileOutputStream(f);
        out.write(data);
        out.close();
    }

    private static String encodeCompressedBase64(byte[] bytes) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(bytes);
        gzip.close();
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    private static byte[] decodeCompressedBase64AsBytes(String base64Code) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(base64Code)));
        byte[] buffer = new byte[256];
        int size;
        while ((size = gzip.read(buffer)) >= 0) {
            out.write(buffer, 0, size);
        }
        return out.toByteArray();
    }

    public static void recoveryFile(String filename, String base64Code) {
        File f = new File(filename);
        try {
            if (!f.exists()) {
                writeFile(f, decodeCompressedBase64AsBytes(base64Code));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void defaultProperties() {
        System.setProperty("catalina.home", tomcatDirectory);
        System.setProperty("catalina.base", currentDirectory);
    }

    private static void defaultDirectories(String baseDirectory) {
        File dir_logs = new File(baseDirectory + "/logs");
        File dir_temp = new File(baseDirectory + "/temp");
        File dir_work = new File(baseDirectory + "/work");
        File dir_conf = new File(baseDirectory + "/conf");
        File dir_webapps = new File(baseDirectory + "/webapps");
        File dir_root = new File(baseDirectory + "/webapps/ROOT");
        System.out.printf("%s: new?=%b\n", dir_logs, dir_logs.mkdir());
        System.out.printf("%s: new?=%b\n", dir_temp, dir_temp.mkdir());
        System.out.printf("%s: new?=%b\n", dir_work, dir_work.mkdir());
        System.out.printf("%s: new?=%b\n", dir_conf, dir_conf.mkdir());
        if (dir_webapps.mkdir()) {
            System.out.printf("%s: new?=%b\n", dir_root, dir_root.mkdir());
            try {
                writeFile(
                        new File(baseDirectory + "/webapps/ROOT/index.jsp"),
                        "<html><head></head><body>Hello</body></html>".getBytes()
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void defaultConfiguration() {
        String baseDirectory = System.getProperty("catalina.base");
        if (!baseDirectory.equals(currentDirectory)) return;
        defaultDirectories(baseDirectory);

        // // ?
        // recoveryFile(
        //         String.format("%s/conf/%s", baseDirectory, "?"),
        //         ""
        // );

        // context.xml
        recoveryFile(
                String.format("%s/conf/%s", baseDirectory, "context.xml"),
                "H4sIAAAAAAAAALOxr8jNUShLLSrOzM+zVTLUM1BSSM1Lzk/JzEu3VQoNcdO1ULK347Jxzs8rSa0oseNSAAKb8MSS5IzUlKDU4vzSouRUu3BXJ11PPzf98tQkPaB5NvroCrhs9GEmAACXp2j+cgAAAA=="
        );

        // tomcat-users.xml
        recoveryFile(
                String.format("%s/conf/%s", baseDirectory, "tomcat-users.xml"),
                "H4sIAAAAAAAAAJWQMQ7CMAxF957CeMaUbgxJu3ECOECaRG2lJkFJChyfhCKkVgjB8mXrfz9bZs3djHDVPgzOcqx2ewRtpVOD7TieT0c6YFMXLDojRaQppGRqN0QFAPNu1JDFCqM5ziEs6w9erqqXlSmQZTEGFxHCzXnFkZkpRGo1yV7YTqsan6SwWrHitC72/1C2X26arZ9gbwpR+ky5+NQDJXz+DF8BAAA="
        );

        // web.xml
        recoveryFile(
                String.format("%s/conf/%s", baseDirectory, "web.xml"),
                "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"
        );
    }

    public static void loadProperties(String filename) {
        defaultProperties();
        if (null != filename) {
            Properties properties = new Properties();
            InputStream in = TomcatResources.class.getClassLoader()
                    .getResourceAsStream(String.format("%s.properties", filename));
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
        defaultConfiguration();
    }

    public static void main(String[] args) throws IOException {
        String base = "E:\\Userprofile\\Notes\\HelloJavaWeb\\notes\\tomcat\\Server\\src\\conf\\";
        String filename = "web.xml";
        byte[] data = readFile(new File(base + filename));
        writeFile(new File(base + filename + ".64.txt"), encodeCompressedBase64(data).getBytes());
    }

}
