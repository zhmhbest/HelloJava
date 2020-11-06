import org.apache.catalina.startup.Bootstrap;

public class TomcatRunner {
    public static void main(String[] args) {
        TomcatResources.loadProperties("conf/runner");
        Bootstrap.main(args);
    }
}
