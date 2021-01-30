package aop;

public class User implements UserInterface {
    public void hi() {
        System.out.print("Hi!\n");
    }
    public void sayHello(String name) {
        System.out.printf("%s: Hello\n", name);
    }
    public void sayGoodbye(String name) {
        System.out.printf("%s: Goodbye\n", name);
    }
}
