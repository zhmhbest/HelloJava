package org.example;
/*
 * org.example.base.collection.Hello
 */
//import java.lang.*;

public class Hello {
    public static String getType(Object ref) {
        return ref.getClass().getName();
    }
    public static void main(String[] args) {
        System.out.println("org.example.base.collection.Hello");

        /*
         * Byte
         * 取值范围: [-2^7, 2^7-1] = [-128, 127]
         */
        byte tmp_byte = 0;
        System.out.printf(
                "%s [-%x, %x]\n", getType(tmp_byte), Byte.MIN_VALUE, Byte.MAX_VALUE
        );

        /*
         * Short
         * 取值范围: [-2^15, 2^15-1] = [-32768, 32767]
         */
        short tmp_short = 0;
        System.out.printf(
                "%s [-%x, %x]\n", getType(tmp_short), Short.MIN_VALUE, Short.MAX_VALUE
        );

        /*
         * Integer
         * 取值范围: [-2^31, 2^31-1]
         */
        int tmp_int = 0;
        System.out.printf(
                "%s [-%x, %x]\n", getType(tmp_int), Integer.MIN_VALUE, Integer.MAX_VALUE
        );

        /*
         * Long
         * 取值范围: [-2^63, 2^63-1]
         */
        long tmp_long = 0;
        System.out.printf(
                "%s [-%x, %x]\n", getType(tmp_long), Long.MIN_VALUE, Long.MAX_VALUE
        );

        /*
         * Float
         */
        float tmp_float = 0f;
        System.out.printf(
                "%s [-%e, %e]\n", getType(tmp_float), Float.MIN_VALUE, Float.MAX_VALUE
        );

        /*
         * Double
         */
        double tmp_double = 0d;
        System.out.printf(
                "%s [-%e, %e]\n", getType(tmp_double), Double.MIN_VALUE, Double.MAX_VALUE
        );

        /*
         * Boolean
         */
        boolean tmp_boolean = false;
        System.out.println(getType(tmp_boolean));

        /*
         * Character
         */
        char tmp_char = '\n';
        System.out.println(getType(tmp_char));

        // If
        if (false) {
            // do-something
        } else if (false) {
            // do-something
        } else {
            // do-something
        }

        // Switch
        for (int i=0; i<3; i++) {
            switch(i){
                case 0 :
                    System.out.println("Switch_A");
                    break;
                case 1 :
                    System.out.println("Switch_B");
                    break;
                default :
                    System.out.println("Switch_C");
            }
        }

        // While
        while (true) {
            // do-something
            break; // continue;
        }

        // do-while
        do {
            // do-something
            break; // continue;
        } while (true);

        // for
        for(;;) {
            break; // continue;
        }

        // for:
        int[] arr = {1, 2, 3};
        for (int i : arr) {
            System.out.println(i);
        }

        // try-catch
        try {
            // do-something
        } catch (Exception e) {
            // deal with exception.
        } finally {
            // do-something
        }
    }
}