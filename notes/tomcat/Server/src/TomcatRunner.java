import org.apache.catalina.startup.Bootstrap;

public class TomcatRunner {
    public static void main(String[] args) {
        TomcatResources.defaultProperties();
        TomcatResources.loadProperties("conf/runner");
        TomcatResources.defaultConfigurations();
        Bootstrap.main(args);
    }
}
