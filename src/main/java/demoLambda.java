/*
 * Lambda表达式是Java8的新特性
 */

interface OperationDo {
    void run();
}

interface OperationMath {
    int run(int a, int b);
}

public class demoLambda {
    public static void main(String[] args) {
        OperationDo say = () -> System.out.println("Hello lambda");
        say.run();

        OperationMath add = (a, b) -> a + b;
        System.out.println(add.run(6, 2));

        OperationMath sub = (int a, int b) -> a - b;
        System.out.println(sub.run(6, 2));

        OperationMath mul = (a, b) -> { return a * b; };
        System.out.println(mul.run(6, 2));

        OperationMath dev = (int a, int b) -> { return a / b; };
        System.out.println(dev.run(6, 2));
    }
}

