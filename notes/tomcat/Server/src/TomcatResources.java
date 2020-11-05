import org.apache.catalina.startup.Bootstrap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

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
        if ( ! baseDirectory.equals(currentDirectory)) return;
        defaultDirectories(baseDirectory);
        defaultXMLContext(baseDirectory);
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
    private static void defaultXMLContext(String baseDirectory) {
        File file_context = new File(baseDirectory + "/conf/context.xml");
        if ( ! file_context.exists()) {
            try {
                FileWriter writer = new FileWriter(file_context, true);
                writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.append("<Context>");
                writer.append("    <WatchedResource>WEB-INF/web.xml</WatchedResource>");
                writer.append("</Context>");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
