package org.example.base;/*
 * Scanner: 从键盘获取输入
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class HelloScanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Push integer here:");
        try {
            System.out.println(sc.nextInt());
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("NaI");
        }

        System.out.print("Push float here:");
        try {
            System.out.println(sc.nextFloat());
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("NaF");
        }

        System.out.print("Push boolean here:");
        try {
            System.out.println(sc.nextBoolean());
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("NaB");
        }

        System.out.print("Push string here:");
        System.out.println(sc.nextLine());
    }
}