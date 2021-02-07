
public class HelloJNI {
    private static String getCurrentDirectoryPath() {
        StringBuilder builder = new StringBuilder();
        builder.append(HelloJNI.class.getResource("/").getPath());
        String[] packageArray = HelloJNI.class.getName().split("\\.");
        for (int i=0; i<packageArray.length-1; i++) {
            builder.append(packageArray[i]);
        }
        return builder.toString();
    }
    public final static String CURRENT_DIRECTORY_PATH = getCurrentDirectoryPath();

    static {
        System.load(CURRENT_DIRECTORY_PATH + "libjni.dll");
        // System.loadLibrary("");
        // jni.dll (Windows) or libjni.so (Unixes)
    }
    private native void sayHello();
    public static void main(String[] args) {
        new HelloJNI().sayHello();
    }
}
