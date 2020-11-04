import org.apache.catalina.startup.Bootstrap;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.io.InputStream;


public class TomcatRunner {
    private static final String currentDirectory =
            new File(TomcatRunner.class.getResource("/").getPath()).getAbsolutePath()
                    .replaceAll("\\\\", "/");
    private static final String tomcatBinaryDirectory =
            new File(Bootstrap.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent()
                    .replaceAll("\\\\", "/");
    private static final String tomcatDirectory =
            new File(tomcatBinaryDirectory).getParent()
                    .replaceAll("\\\\", "/");

    private static void loadProperties(String filename) {
        // 默认配置
        System.setProperty("catalina.home", tomcatDirectory);
        System.setProperty("catalina.base", currentDirectory);
        if (null==filename) return;
        // 自定义配置
        Properties properties = new Properties();
        InputStream in = TomcatRunner.class.getClassLoader().getResourceAsStream(String.format("%s.properties", filename));
        if (null==in) {
            System.out.printf("Can not find %s\n", filename);
            return;
        }
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Enumeration keys = properties.propertyNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement().toString();
            System.setProperty(key, properties.getProperty(key));
        }
        /*
            # 程序目录
            catalina.home=
            # 运行目录
            catalina.base=
            # 缓存目录
            java.io.tmpdir=
            # 安全协议
            java.security.policy=
            # 日志配置
            java.util.logging.config.file==
            java.util.logging.manager=org.apache.juli.ClassLoaderLogManager
        */
    }

    private static void defaultConfiguration() {
        // Default Directory
        String baseDirectory = System.getProperty("catalina.base");
        if ( ! baseDirectory.equals(currentDirectory)) return;
        File dir_logs = new File(baseDirectory + "/logs");
        File dir_temp = new File(baseDirectory + "/temp");
        File dir_work = new File(baseDirectory + "/work");
        File dir_conf = new File(baseDirectory + "/conf");
        System.out.printf("%s: new?=%b\n", dir_logs, dir_logs.mkdir());
        System.out.printf("%s: new?=%b\n", dir_temp, dir_temp.mkdir());
        System.out.printf("%s: new?=%b\n", dir_work, dir_work.mkdir());
        System.out.printf("%s: new?=%b\n", dir_conf, dir_conf.mkdir());
    }

    public static void main(String[] args) {
        loadProperties("tomcat");
        defaultConfiguration();
        Bootstrap.main(args);
    }
}
